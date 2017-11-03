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
    	
    	function Person (fullname,age,bio,ruta){
    		    this.name = fullname;
    		    this.age = age;
    		    this.bio = bio;
    		    this.ruta = ruta;
    		};
    	
    	$scope.team = [];
    	var rodri = new Person("Rodrigo Vazquez",22,"Universidad Tecnologica Nacional - FRBA" +
    			"Trabaja en Ford Motor Company","content/images/rodrigo.png");
    	var koiffo = new Person("Iván Koiffman",22,"Universidad Tecnologica Nacional - FRBA" +
    			"Trabaja en Base4Security","content/images/ivan.png");
    	var santi = new Person("Santiago Varela",23,"Universidad Tecnologica Nacional - FRBA" +
    			"Trabaja en Siemens","content/images/santiago.png");
    	var jony = new Person("Jonatan Martinez",22,"Universidad Tecnologica Nacional - FRBA" +
    			"Trabaja en EY","content/images/rodrigo.png");
    	var nico = new Person("Nicolas Taccari",22,"Universidad Tecnologica Nacional - FRBA" +
    			"Trabaja en ExxonMobil","content/images/rodrigo.png");
    	
    	$scope.team.push(rodri);
    	$scope.team.push(koiffo);
    	$scope.team.push(santi);
    	$scope.team.push(jony);
    	$scope.team.push(nico);
    }
})();
