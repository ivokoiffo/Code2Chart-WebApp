//uso factory que es un singleton por default. lo que 
//devuelve se puede reutilizar en cualquier momento
angular.module('code2chart')
    .factory('descargarFactory', ['$http', function($http) {
    
    var urlBase = '/api/getApk';
    var descargarFactory = {};
    
    descargarFactory.descargarApk = function () {
        return $http( {
          method:'GET',
          url: urlBase,
          responseType: 'arraybuffer',
          
        }).then(function (response) {
        	var blob = new Blob([response.data], {type: "application/octet-stream"});
        	return blob;
          });
    };
    
    return descargarFactory;
}]);
