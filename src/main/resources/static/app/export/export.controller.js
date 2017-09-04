(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('exportController', exportController);
 
    exportController.$inject = [];
 
    function exportController() {
        var vm = this;
        vm.title = 'How do you want to export your file?';
        vm.formData = {};
        
        vm.$onInit = activate;

        ////////////////

        function activate() {
            // get data from the parent component
            vm.formData = vm.parent.getData();
            console.log('export feature loaded!');
        }
    }
})();