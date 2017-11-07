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
        var saveLocal = true;
                
        vm.redirectToNewForm = function(){
        	$location.url('/fileUpload.html');
        };
        
        vm.generateLocal = function(){
        	saveLocal = true;
        	vm.generate();
        }
        
        vm.generateDrive = function(){
        	saveLocal = false;
        	vm.generate();
        }
        
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
	    			if(saveLocal){
		    			saveAs(response, fileName);
	    			}else{
	    				var driveFile = new File([response],fileName);
	    				var filesArray = [];
	    				filesArray.push(driveFile);
	    				$scope.upload(filesArray);
	    			}
	    			
	    		}, function (error){
	    			alert("not found");
	    			toaster.error("No se ha podido generar el diagrama. Vuelva a intentarlo"); 
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

        $scope.upload=function(filesArray){
            $scope.uploading=true;
            
            var file = filesArray[0];

            driveService.insertFile(file, file.name).then(function(link){
                $scope.images.push(link);
            }).finally(function(){
                $scope.uploading=false;
            });
        }
    }
})();