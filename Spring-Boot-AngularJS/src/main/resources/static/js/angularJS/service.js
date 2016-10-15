app.factory('auth', function($http, $location, params) {

	var token = "";
	var usuario = {
		username : "",
		nome : "",
		email : "",
		roles : "",
		nomesRoles : function() {
			var roles = [];
			for (var i = 0; i < this.roles.length; i++)
				roles.push(this.roles[i].nome);
			return roles;
		},
		hasRole : function(role) {
			for (var i = 0; i < this.roles.length; i++)
				if (this.roles[i].nome === role)
					return true;
			return false;
		}
	};

	function clear() {
		token = "";
		usuario = {
			username : "",
			nome : "",
			email : "",
			roles : "",
			nomesRoles : function() {
				var roles = [];
				for (var i = 0; i < this.roles.length; i++)
					roles.push(this.roles[i].nome);
				return roles;
			},
			hasRole : function(role) {
				for (var i = 0; i < this.roles.length; i++)
					if (this.roles[i].nome === role)
						return true;
				return false;
			}
		};
	}

	function createUsuario(data) {
		var userRoles = [];
		for (var i = 0; i < data.roles.length; i++) {
			userRoles.push({
				nome : data.roles[i].nome
			});
		}

		usuario = {
			username : data.username,
			nome : data.nome,
			email : data.email,
			roles : userRoles,
			nomesRoles : function() {
				var roles = [];
				for (var i = 0; i < this.roles.length; i++)
					roles.push(this.roles[i].nome);
				return roles;
			},
			hasRole : function(role) {
				for (var i = 0; i < this.roles.length; i++)
					if (this.roles[i].nome === role)
						return true;
				return false;
			}
		};

		return usuario;
	}

	function getToken() {
		return token;
	}
	function setToken(newToken) {
		token = newToken;
	}

	function getUsuario() {
		return usuario;
	}

	function setUsuario(newUsuario) {
		usuario = createUsuario(newUsuario);
	}

	function refreshUser() {
		var req = {
			method : "GET",
			url : "/api/usuario/logado",
			headers : {
				"X-Auth-Token" : getToken()
			}
		};

		$http(req).success(function(data) {
			setUsuario(createUsuario(data));
		});
	}

	function hasRole(roles) {
		for (var j = 0; j < roles.length; j++) {
			for (var i = 0; i < this.getUsuario().nomesRoles().length; i++) {
				if (this.getUsuario().nomesRoles()[i] == "ROLE_" + roles[j])
					return true;
			}
		}
		return false;
	}

	function routeAccess(roles) {
		if (!this.hasRole(roles)) {
			$location.path('/acessoNegado');
		}

		return true;
	}

	return {
		getToken : getToken,
		setToken : setToken,
		getUsuario : getUsuario,
		setUsuario : setUsuario,
		refreshUser : refreshUser,
		clear : clear,
		hasRole : hasRole,
		routeAccess : routeAccess
	};
});

app.factory('params', function() {
	var parameters = new Map();

	function set(params) {
		parameters = new Map(params);
	}

	function get(key) {
		return parameters.get(key)
	}

	function has(key) {
		return parameters.has(key);
	}

	return {
		set : set,
		get : get,
		has : has
	}
});

app.factory('role', function($http, auth) {
	function listar() {
		var roles = [];
		var req = {
			method : "GET",
			url : "/api/role/lista",
			headers : {
				"X-Auth-Token" : auth.getToken()
			}
		};

		$http(req).success(function(data) {
			for (var i = 0; i < data.length; i++) {
				roles.push(data[i].nome);
			}
		});

		return roles;
	}

	return {
		listar : listar
	}
});

app.factory('usuario', function($http, auth) {
	function createUsuario(data) {
		var userRoles = [];
		for (var i = 0; i < data.roles.length; i++) {
			userRoles.push({
				nome : data.roles[i].nome
			});
		}

		usuario = {
			username : data.username,
			nome : data.nome,
			email : data.email,
			roles : userRoles,
			nomesRoles : function() {
				var roles = [];
				for (var i = 0; i < this.roles.length; i++)
					roles.push(this.roles[i].nome);
				return roles;
			},
			hasRole : function(role) {
				for (var i = 0; i < this.roles.length; i++)
					if (this.roles[i].nome === role)
						return true;
				return false;
			}
		};

		return usuario;
	}

	function listar() {
		var usuarios = [];
		var req = {
			method : "GET",
			url : "/api/usuario",
			headers : {
				"X-Auth-Token" : auth.getToken()
			}
		};

		$http(req).success(function(data) {
			for (var i = 0; i < data.length; i++) {
				usuarios.push(createUsuario(data[i]));
			}
		});

		return usuarios
	}

	function editar(origUsername, usuario) {
		var req = {
			method : "PUT",
			url : "/api/usuario/" + origUsername,
			headers : {
				"X-Auth-Token" : auth.getToken()
			},
			data : usuario
		};
		var ret;

		return $http(req);
	}
	
	function cadastrar(usuario) {
		var req = {
			method : "POST",
			url : "/api/usuario/",
			headers : {
				"X-Auth-Token" : auth.getToken()
			},
			data : usuario
		};
		return $http(req)
	}
	
	function remover(username){
		var req = {
			method : "DELETE",
			url : "/api/usuario/",
			headers : {
				"X-Auth-Token" : auth.getToken()
			},
			data : username
		};
		
		return $http(req);
	}
	
	return {
		createUsuario : createUsuario,
		listar : listar,
		editar : editar,
		cadastrar : cadastrar,
		remover : remover
	}
});