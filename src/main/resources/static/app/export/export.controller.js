(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('exportController', exportController);
 
    exportController.$inject = ['$location'];
 
    function exportController($location) {
        var vm = this;
        vm.title = 'How do you want to export your file?';
        vm.formData = {};
        
        vm.$onInit = activate;
        
        vm.redirectToNewForm = function(){
        	$location.url('/fileUpload.html');
        };

        ////////////////

        function activate() {
            // get data from the parent component
            vm.formData = vm.parent.getData();
            console.log('export feature loaded!');
        }
    }
})();