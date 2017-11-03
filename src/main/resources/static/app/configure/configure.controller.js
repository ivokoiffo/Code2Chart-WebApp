(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('configureController', configureController);
 
    configureController.$inject = [];
 
    function configureController() {
        var vm = this;
        vm.title = 'Ingrese la información general de su diagrama';
        vm.formData = {};
        
        vm.hasAllParams = function(){
        	return ( !(vm.formData.name.length > 0) ||
        		   !(vm.formData.author.length > 0) ||
        		   !(vm.formData.description.length > 0) );
        };
        
        vm.$onInit = activate;

        ////////////////

        function activate() {
            // get data from the parent component
            vm.formData = vm.parent.getData();
            console.log('configure feature loaded!');
        }
    }
})();