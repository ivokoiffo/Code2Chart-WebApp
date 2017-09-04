(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('configureController', configureController);
 
    configureController.$inject = [];
 
    function configureController() {
        var vm = this;
        vm.title = 'Enter your file information';
        vm.formData = {};
        
        vm.$onInit = activate;

        ////////////////

        function activate() {
            // get data from the parent component
            vm.formData = vm.parent.getData();
            console.log('configure feature loaded!');
        }
    }
})();