(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('exportController', exportController);
    	
    exportController.$inject = ['$location','dataFactory','toaster','$scope','gapiAuthService','driveService'];
    	
    function exportController($location,dataFactory,toaster,$scope,gapiAuthService,driveService) {
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
        
        $scope.checkingLogin=true;
        gapiAuthService.checkLogin().then(function(){
            $scope.loggedIn=true;
        },function(){
            $scope.loggedIn=false;
        }).finally(function(){
            $scope.checkingLogin=false;
        });

        $scope.login=function(){
            gapiAuthService.login().then(function(){
                $scope.loggedIn=true;
            },function(authResult){
                $scope.loggedIn = false;
                console.err(authResult);
            })
        };

        //Drive

        $scope.images=[];

        $scope.clickFileUpload=function(){
            document.getElementById('uploadImage').click();
        };

        $scope.upload=function(){
            $scope.uploading=true;
            var allFiles=document.getElementById('uploadImage').files;
            var file=allFiles[0];

            driveService.insertFile(file, file.name).then(function(link){
                $scope.images.push(link);
            }).finally(function(){
                $scope.uploading=false;
            });
        }
    }
})();