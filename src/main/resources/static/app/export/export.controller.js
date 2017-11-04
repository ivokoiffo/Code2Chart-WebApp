(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('exportController', exportController);
    	
    exportController.$inject = ['$location','dataFactory','toaster'];
    	
    function exportController($location,dataFactory,toaster) {
        var vm = this;
        vm.title = '¿Que desea hacer con su archivo?';
        vm.formData = {};
                
        vm.redirectToNewForm = function(){
        	$location.url('/fileUpload.html');
        };
        
        vm.generate = function(){
        	
        	var formData = new FormData();
        	
        	if(vm.parent.getFile()[0] == null){
        		var dummyFile = new File(["foo"], "foo.txt");
        		vm.parent.getFile().push(dummyFile);
        	}
        	formData.append("file", vm.parent.getFile()[0]);
        	formData.append('model', new Blob([JSON.stringify(vm.parent.getData())], {
        	                type: "application/json"
        	            }));
	
	    	dataFactory.generarDiagrama(formData)
	    		.then(function (response){
	    			var fileName = vm.parent.getData().name + '.png';
	    			saveAs(response, fileName);
	    		}, function (error){
	    			toaster.error("No se ha podido eliminar el elemento"); 
	    		});
        };
    }
})();