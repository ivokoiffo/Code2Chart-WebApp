(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .component('aboutComponent', {
            templateUrl:  'app/about/about.html',
            controller: 'aboutController',
            controllerAs: 'about'
        })
})();