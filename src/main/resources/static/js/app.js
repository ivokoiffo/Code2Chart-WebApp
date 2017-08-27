angular.module("code2chart",['ngRoute','ui.bootstrap'])
	
	.config(['$routeProvider',function($routeProvider) {
	  $routeProvider
	  	.when('/', {
	    	controller: 'mainController',
	    	templateUrl: '/index.html'
	  })
	  .otherwise({
	    redirectTo: '/'
	  });
	}]);