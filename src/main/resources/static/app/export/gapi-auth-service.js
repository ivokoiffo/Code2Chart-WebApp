'use strict';

angular.module('code2chart')
    .factory('gapiAuthService',['$q', function ($q) {
        var client_id="549366170263-uomjfa7triu4etuvf9q8i8tfuhdc2pfb.apps.googleusercontent.com";
        var scope=['https://www.googleapis.com/auth/drive.file'];

        function getConfig(immediate){
            return {
                'client_id': client_id,
                'scope': scope,
                immediate: immediate
            }
        }

        
        return {

            login:function() {
                var deferred = $q.defer();

                gapi.auth.authorize(getConfig(false), function (authResult) {
                    if (authResult && !authResult.error) {
                        deferred.resolve(gapi.auth.getToken().access_token);
                    } else {
                        deferred.reject(authResult);
                    }
                })

                return deferred.promise;
            }
        }


    }]);
