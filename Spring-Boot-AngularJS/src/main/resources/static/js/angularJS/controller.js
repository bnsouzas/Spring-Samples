app.controller('mainController', function($scope) {
	$scope.$on('$viewContentLoaded', function() {
		initUibTooltips();
	});
});

app.controller('headerController', function($scope, $http, $location, auth, params) {
	$scope.$watch(function() {
		return auth.getUsuario();
	}, function() {
		$scope.usuario = auth.getUsuario();
	});


	$scope.logout = function() {
		console.log('logout');
		var req = {
			method : "POST",
			url : "/logout",
			headers : {
				"X-Auth-Token" : auth.getToken()
			}
		}
		$http(req).success(function() {
			params.set([['logout','']]);
			auth.clear();
			$location.path("/login");
		});
	}
	
});

app.controller('loginController', function($scope, $http, $location, auth, params) {
	$scope.headingTitle = "Acessar";
	$scope.error = "";
	$scope.logout = "";
	
	if (params.has('logout')){
		$scope.logout = "Você foi desconectado com sucesso.";
	}
	
	$scope.init = function(){
		$scope.username = "";
		$scope.password = "";
	}
	
	$scope.login = function() {
		var data = {
			"username" : $scope.username,
			"password" : $scope.password
		};
		
		var transform = function(data) {
			return $.param(data);
		}

		var req = {
			method : "POST",
			url : "/login",
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded; charset=UTF-8"
			},
			transformRequest : transform,
			data : data
		};
		
		$http(req)
			.success(function(data, status, headers, config) {
					auth.setToken(headers('x-auth-token'));
					auth.setUsuario(data);
					$location.path("/");
					$scope.error = "";
				})
			.error(function(data, status, headers, config) {
					$scope.error = "Usuário ou senha inválido.";
					$scope.init();
				});
	}
});

app.controller('usuarioController', function($scope, $location, usuario, role, params) {
	$scope.headingTitle = "User List";
	if ($location.path() == "/usuario")
	$scope.usuarios = usuario.listar();
	$scope.sucesso = "";
	
	if (params.has('usuario')){
		$scope.usuario = params.get('usuario');
		$scope.origUsername = $scope.usuario.username;
		$scope.allRoles = role.listar();
	}
	
	$scope.toogleCheckRoles = function(role){
		if ($scope.usuario.hasRole(role)){
			objectRole = { nome: role }
			$scope.usuario.roles = $scope.usuario.roles.filter(function(el){
				return el.nome !== role;
			})
		} else {
			$scope.usuario.roles.push({ nome: role });
		}
	}
	
	$scope.callEdit = function(paramUsuario){
		params.set([['usuario',paramUsuario]]);
		$location.path("/usuario/edit");
	};
	
	$scope.edit = function(){
		var promisse = usuario.editar($scope.origUsername, $scope.usuario);
		promisse.then(function(response) {
			$location.path("/usuario/");
	    }, function(response) {
			$scope.validErrors = response.data['errors'];
			
	    });
	}
	
	$scope.signin = function(){
		var promisse = usuario.cadastrar(usuario.createUsuario($scope.usuario));
		promisse.then(function(response) {
			$location.path("/login");
	    }, function(response) {
			$scope.validErrors = response.data['errors'];
			console.log($scope.validErrors);
	    });
		
	}
	
	$scope.remover = function(paramUsuario){
		if (confirm("Tem certeza que deseja remover o usuário " + paramUsuario.username + "?")){
			var promisse = usuario.remover(paramUsuario.username);
			promisse.then(function(response) {
				$location.path("/usuario/");
		    }, function(response) {
				$scope.validErrors = response.data['errors'];
		    });
		}
	}
});

app.controller('rolesController', function($scope) {
	$scope.headingTitle = "Roles List";
});