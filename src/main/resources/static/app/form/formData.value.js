(function () {
    'use strict';
 
    angular
        .module('code2chart')
        .value('FormDataModel', FormDataModel);
 
    function FormDataModel() {
        this.githubUrl = '';
        this.localPath = null;
        this.name = '';
        this.description = '';
        this.author = '';
    }
})();