app.directive('isAuth', function($compile, auth) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var origElement = element.html();
			scope.$watch(function() {
				return auth.getUsuario();
			}, function() {
				element.empty();
				if (auth.getToken() != "" && attrs.isAuth == 'true')
					element.append(origElement);
				if (auth.getToken() == "" && attrs.isAuth == 'false')
					element.append(origElement);
				$compile(element.contents())(scope);
			})
		}
	}
});

app.directive('hasRole', function(auth) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var origElement = element.html();
			scope.$watch(function() {
				return auth.getUsuario();
			}, function() {
				element.empty();
				if (auth.getUsuario().hasRole("ROLE_" + attrs.hasRole))
					element.append(origElement);
			})
		}
	}
});

app.directive('btnErrors', function($timeout) {
	return {
		restrict : 'AE',
		transclude: false,	
		scope: {
			field: '@',
			errors: '@'
		},
		templateUrl: "/templates/btnError.html",
		link: {
			pre: function($scope, $element, $attrs){
					$attrs.$observe('errors', function(){
						$scope.message = '';
						$scope.hasErrors = false;
						if ($scope.errors){
							var errors1 = JSON.parse($scope.errors);
							for(var key in errors1){
								if (key === $scope.field){
									$scope.message = $scope.message + errors1[key];
									$scope.hasErrors = true;
								}
							}
						}
						
						$timeout(initUibTooltips,0);
					})
			}
		}
	}
});