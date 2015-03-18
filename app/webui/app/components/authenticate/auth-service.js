'use strict';

angular
  .module('simple-taskboard.webui.components.authenticate')
  .factory('authService', [ '$http', '$rootScope', 'authApi', 'validateServiceReponseHandler',

  function($http, $rootScope, authApi, validateServiceReponseHandler){
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
        return validateServiceReponseHandler.execute(
          $http.post(authApi.validateUrl, {
            authId: id,
            password: password
          })
        );
      }
    };

  }

]);
