(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .component('formComponent', {
            templateUrl:  'app/form/form.html',
            controller: 'FormController',
            controllerAs: 'vm'
        })
})();