(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('fileUploadController', fileUploadController);
    	
    fileUploadController.$inject = [];
 
    function fileUploadController() {
        var vm = this;
        vm.title = 'Choose the file you want to convert';
        vm.formData = {};
        
        vm.hasGithubUrl = function(){
        	return (vm.formData.githubUrl.length > 0);
        };
        
        vm.hasLocalPath = function(){
        	return (vm.formData.localPath != null);
        }; 
                                
        vm.anyFile = function(){
        	return !( vm.hasGithubUrl() || vm.hasLocalPath() ); 
        };

        vm.$onInit = activate;
        
        ////////////////

        function activate() {
            // get data from the parent component
            vm.formData = vm.parent.getData();
            console.log('fileUpload feature loaded!');
        }       
    }
})();
