(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('exportController', exportController);
    	
    exportController.$inject = ['$location','dataFactory','toaster'];
    	
    function exportController($location,dataFactory,toaster) {
        var vm = this;
        vm.title = 'Que desea hacer con su archivo?';
        vm.formData = {};
                
        vm.redirectToNewForm = function(){
        	$location.url('/fileUpload.html');
        };
        
        vm.generate = function(){
	
	    	dataFactory.generarDiagrama(vm.parent.getData())
	    		.then(function onSuccess(response){
	    			vm.diagrama = response.data;
	    			var blob = new Blob([vm.diagrama], {type: "application/octet-stream"});
	    			var fileName = vm.parent.getData().name + '.png';
	    			saveAs(blob, fileName);
	    		}, function onFail(error){
	    			toaster.error("No se ha podido eliminar el elemento"); 
    		});
        };
    }
})();