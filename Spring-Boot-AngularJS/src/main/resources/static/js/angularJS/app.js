var app = angular.module('springBootAngularJS', [ 'ngRoute', 'ngResource', 'ui.bootstrap' ]);
app.config(function($routeProvider) {
	$routeProvider
	.when('/acessoNegado',{
		templateUrl: '/views/acessoNegado.html',
	})
	.when('/usuario', {
		templateUrl : '/views/usuario/list.html',
		controller : 'usuarioController',
		resolve : {
			checkRoles : function(auth) {
				return auth.routeAccess(['ADMIN']);
			}
		}
	}).when('/usuario/edit', {
		templateUrl : '/views/usuario/edit.html',
		controller : 'usuarioController',
		resolve : {
			checkRoles : function(auth) {
				return auth.routeAccess(['ADMIN']);
			}
		}
	}).when('/usuario/signin', {
		templateUrl : '/views/usuario/signin.html',
		controller : 'usuarioController'
	}).when('/roles', {
		templateUrl : '/views/regras.html',
		controller : 'rolesController'
	}).when('/login', {
		templateUrl : '/views/login.html',
		controller : 'loginController'
	}).otherwise({
		redirectTo : '/'
	});
});