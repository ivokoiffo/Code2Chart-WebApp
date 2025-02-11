(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('fileUploadController', fileUploadController);
    	
    fileUploadController.$inject = ['$scope','toaster'];
 
    function fileUploadController($scope,toaster) {
        var vm = this;
        vm.title = 'Elija el archivo que quiera convertir';
        vm.formData = {};
        $scope.file = [];

        //3 listener para el evento que lanza la directiva
        $scope.$on("seletedFile", function (event, args) {  
            $scope.$apply(function () {  
                //se agrega el file al array, luego se appendea en el json  
                $scope.file.push(args.file);
            });  
        });  
        
        
        $scope.reloadRoute = function() {
        	$route.reload();
        }
        
        $scope.clearFile = function(){
        	if($scope.file.length > 0){
        		$scope.file.pop();
        	}
        };        
        
        vm.hasGithubUrl = function(){
        	var github = vm.formData.githubUrl;
        	if(github.length > 0){
	        	var patron = new RegExp("^(https:\/\/)raw\\.githubusercontent\\.com\/+.+(\\.c)$");
	        	return ( patron.test(github) && !(/\s/g.test(github)));
        	}
        	return false;
        	//toaster.error("Ingrese una url correcta");
        };
        
        vm.hasLocalPath = function(){
        	return ($scope.file.length > 0);
        }; 
                                
        vm.anyFile = function(){
        	return !( vm.hasGithubUrl() || vm.hasLocalPath()); 
        };

        vm.$onInit = activate;
        
        ////////////////

        function activate() {
            // get data from the parent component
            vm.formData = vm.parent.getData();
            $scope.file = vm.parent.getFile();
            console.log('fileUpload feature loaded!');
        }       
    }
})();
