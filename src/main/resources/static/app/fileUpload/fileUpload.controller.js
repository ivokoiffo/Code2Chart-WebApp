(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('fileUploadController', fileUploadController);
    	
    fileUploadController.$inject = ['$scope'];
 
    function fileUploadController($scope) {
        var vm = this;
        vm.title = 'Elija el archivo que quiera convertir';
        vm.formData = {};
        $scope.file = [];
        vm.isFileAbsent = true;
        
        //3 listener para el evento que lanza la directiva
        $scope.$on("seletedFile", function (event, args) {  
            $scope.$apply(function () {  
                //se agrega el file al array, luego se appendea en el json  
                $scope.file.push(args.file);
                vm.isFileAbsent = false;
            });  
        });  
        
        $scope.clearFile = function(){
        	while(($scope.file.length > 0)){
        		$scope.file.pop();
        		vm.isFileAbsent = true;
        	}
        }        
        
        vm.hasGithubUrl = function(){
        	var github = vm.formData.githubUrl;
        	if(github.length > 0){
	        	var patron = new RegExp("((((https|http):\/\/)|www\\.)raw\\.githubusercontent\\.com\/+.+\\.c)|(raw\\.githubusercontent\\.com\/+.+\\.c)");
	        	return (patron.test(github))
        	}
        	return false;
        };
        
        vm.hasLocalPath = function(){
        	return ($scope.file.length > 0);
        }; 
                                
        vm.anyFile = function(){
        	return !( vm.hasGithubUrl() || vm.hasLocalPath() ); 
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
