(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('aboutController', aboutController);
 
    aboutController.$inject = ['$scope','$mdDialog'];
 
    function aboutController($scope,$mdDialog) {
    	
    	$scope.openFromLeft = function() {
    		$mdDialog.show(
    			$mdDialog.alert()
    			.clickOutsideToClose(true)
		        .title('Additional Information')
		        .textContent('Closing to the right!')
		        .ariaLabel('Left to right demo')
		        .ok('Nice!')
		        // You can specify either sting with query selector
		        .openFrom('#left')
		        // or an element
		        .closeTo(angular.element(document.querySelector('#right')))
    		);
    	};
    	
    	function Person (fullname,age,bio,minibio,ruta,rutaLinkedin,rutaGithub){
    		    this.name = fullname;
    		    this.age = age;
    		    this.bio = bio;
    		    this.minibio = minibio;
    		    this.ruta = ruta;
    		    this.rutaLinkedin= rutaLinkedin;
    		    this.rutaGithub=rutaGithub;
    		};
    	
    	$scope.team = [];
    	var rodri = new Person("Rodrigo Vazquez",22,"","Trabaja en Ford Motor Company","content/images/rodrigo.png","https://www.linkedin.com/in/rodrigo-vazquez-102b2a114/","https://github.com/rovazquez");
    	var koiffo = new Person("Iván Koiffman",22,"Me intereso en mejorar la seguridad de un sistema a través de la identificación y explotación de vulnerabilidades."
    			,"Trabaja en Base4Security","content/images/ivan.png","https://www.linkedin.com/in/ivankoiffman/","https://github.com/ivokoiffo");
    	var santi = new Person("Santiago Varela",23,"",
    			"Trabaja en Siemens","content/images/santiago.png","https://www.linkedin.com/in/santiago-varela-379658a9/","https://github.com/santiagojvarela");
    	var jony = new Person("Jonatan Martinez",22,"",
    			"Trabaja en EY","content/images/jonatan.png","https://www.linkedin.com/in/jonatan-gabriel-martinez-47a47093/","https://github.com/jonmartinez123");
    	var nico = new Person("Nicolás Taccari",22,"",
    			"Trabaja en ExxonMobil","content/images/rodrigo.png","https://www.linkedin.com/in/nicolas-taccari-84a296139/","https://github.com/nicoTaccari");
    	
    	$scope.team.push(rodri);
    	$scope.team.push(koiffo);
    	$scope.team.push(santi);
    	$scope.team.push(jony);
    	$scope.team.push(nico);
    }
})();
