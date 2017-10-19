(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('exportController', exportController);
    	
    exportController.$inject = ['$location','dataFactory'];
    	
    function exportController($location,dataFactory) {
        var vm = this;
        vm.title = 'How do you want to export your file?';
        vm.formData = {};
                
        vm.redirectToNewForm = function(){
        	$location.url('/fileUpload.html');
        };
        
        vm.generate = function(){
	
			/*if (vm.parent.getData().localPath.file != null) {
			  vm.upload(vm.parent.getData().localPath.file);
			}*/
			
        	dataFactory.generarDiagrama(vm.parent.getData())
        		.then(function(response){
        			vm.diagrama = response.data;
        			console.log(vm.diagrama);
        		}, function(error){
        			vm.status = 'El diagrama no ha podido generarse. Intente nuevamente'; 
        		});
        };
    }
})();