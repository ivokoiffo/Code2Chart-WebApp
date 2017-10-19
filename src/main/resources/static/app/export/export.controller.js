(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('exportController', exportController);
    	
    exportController.$inject = ['$location','dataFactory'];
    	
    function exportController($location,dataFactory) {
        var vm = this;
        vm.title = 'Que desea hacer con su archivo?';
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
        			var file = new Blob([vm.diagrama], { type: 'image/png' });
        	        saveAs(file, vm.parent.getData().name + '.png');
        			console.log(vm.diagrama);
        		}, function(error){
        			vm.status = 'El diagrama no ha podido generarse. Intente nuevamente'; 
        		});
        };
    }
})();