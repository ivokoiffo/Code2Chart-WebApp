(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .controller('homeController', homeController);
 
    homeController.$inject = ['$scope'];
 
    function homeController($scope) {
        var vm = this;
        
        $scope.navItems = [
  	      {value: "/", label: "Code2Chart"},
  	      {value: "/descargar", label: "Descargar"},
  	      {value: "/fileUpload", label: "Usar Online"},
  	    ];
  	
        $scope.currentNavItem = "/";
    }
})();
