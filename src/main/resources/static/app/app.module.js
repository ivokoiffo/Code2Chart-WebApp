(function() {
    'use strict';

    // Creating our angular app and inject ui-router 
    var app = angular.module('code2chart', ['ui.router','ngMaterial','ngFileUpload'])

    // Configuring our states, each one of these is a new stage in the process
    app.config(['$stateProvider', '$urlRouterProvider',

        function($stateProvider, $urlRouterProvider) {

            // Default for unkown URL, redirect to /form/fileUpload
            $urlRouterProvider.otherwise('/');
    
            $stateProvider
                // PARENT STATE: form state
                .state('form', {
                    url: '/form',
                    component: 'formComponent'
                })
                
                .state('home',{
                	url:'/',
                	component: 'homeComponent'
                })
                
                .state('about',{
                	url:'/about',
                	component: 'aboutComponent'
                })
                
                // NESTED STATES: child states of 'form' state 
                // URL will become '/form/fileUpload'
                .state('form.fileUpload', {
                    url: '/fileUpload',
                    component: 'fileUploadComponent'
                })
        
                // URL will become /form/configure
                .state('form.configure', {
                    url: '/configure',
                    component: 'configureComponent'
                })
        
                // URL will become /form/export
                .state('form.export', {
                    url: '/export',
                    component: 'exportComponent'
                })
        }
    ]);
       
})();
