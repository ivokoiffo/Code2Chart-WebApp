(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('descargarController', descargarController);
 
    descargarController.$inject = ['$scope','descargarFactory','toaster'];
 
    function descargarController($scope,descargarFactory,toaster) {
    	
    	$scope.descargar = function(){
    		
	    	descargarFactory.descargarApk()
			.then(function (response){
				var fileName = 'code2chart.apk';
				saveAs(response, fileName);
			}, function (error){
				toaster.error("No se ha podido descargar el archivo"); 
			});
    	}
    }
})();
