(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('ResultController', ResultController);
 
    ResultController.$inject = [];
 
    function ResultController() {
        var vm = this;
        vm.title = 'Thanks for staying tuned!';
        vm.formData = {};
        
        vm.$onInit = activate;

        ////////////////

        function activate() {
            // get data from the parent component
            vm.formData = vm.parent.getData();
            console.log('Result feature loaded!');
        }
    }
})();
