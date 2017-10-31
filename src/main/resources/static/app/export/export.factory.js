//uso factory que es un singleton por default. lo que 
//devuelve se puede reutilizar en cualquier momento
angular.module('code2chart')
    .factory('dataFactory', ['$http', function($http) {
    
    var urlBase = '/api/generarDiagrama';
    var dataFactory = {};
    
    dataFactory.generarDiagrama = function (formData) {
        return $http( {
    	  data: formData,
          method:'POST',
          url: urlBase,
          responseType: 'arraybuffer'
        }).then(function (response) {
        	var blob = new Blob([response.data], {type: "application/octet-stream"});
        	return blob;
          });
    };
    
    return dataFactory;
}]);
