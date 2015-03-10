'use strict';

angular
  .module('simple-taskboard.webui.components.authenticate')
  .factory('authService', ['$http', '$rootScope', 'authApi', function($http, $rootScope, authApi){

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
        }).error(function(){
          console.log('! error');
        });
      }
    };

  }]);
