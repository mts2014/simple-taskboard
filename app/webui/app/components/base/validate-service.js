'use strict';

angular
  .module('simple-taskboard.webui.components.base')
  .factory('validateServiceReponseHandler', ['$q', '$rootScope', function($q, $rootScope){

    return {

      execute: function(httpPromise){

        var defered = $q.defer();

        httpPromise
          .success(function(){

            $rootScope.$broadcast('validate.success');
            defered.resolve();

          }).error(function(data){

            var errors = {};
            angular.forEach(data.errors, function(error){
              if(error.fields.length < 1){
                errors.global = errors.global || [];
                errors.global.push(error);
              }else{
                angular.forEach(error.fields, function(field){
                  errors[field] = errors[field] || [];
                  errors[field].push(error);
                });
              }
            });

            $rootScope.$broadcast('validate.error', errors);
            defered.reject();
          });

        return defered.promise;

      }

    };

  }]);
