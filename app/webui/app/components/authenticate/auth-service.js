'use strict';

angular
  .module('simple-taskboard.webui.components.authenticate')
  .factory('authService', ['$q', '$http', '$rootScope', 'authApi', function($q, $http, $rootScope, authApi){

    var auth = {};
    return {
      auth: auth,
      authenticate: function(id, password){
        $http.post(authApi.authenticateUrl, {
          authId: id,
          password: password
        }).success(function(data){
          jQuery.extend(auth, data.contents.auth);
          $rootScope.$broadcast('success.authenticate');
        }).error(function(data){
          $rootScope.$broadcast('global.error', data.errors);
        });
      },

      validate: function(id, password){

        var defered = $q.defer();

        $http.post('/api/authenticate?validate', {
              authId: id,
              password: password
          }).success(function(){

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
