'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .factory('userService', ['$http', '$rootScope', 'userApi', 'validateServiceReponseHandler',

  function($http, $rootScope, userApi, validateServiceReponseHandler){

    var currentUser = {};

    return {
      currentUser: currentUser,

      register: function(user){

        $http.post(userApi.registerUrl, {

            email: user.email,
            userName: user.name,
            password: user.password,
            confirmPassword: user.confirmPassword

          }).success(function(){
            jQuery.extend(currentUser, user);

            $rootScope.$broadcast('currentuser.changed');
            $rootScope.$broadcast('user.register.success');
            $rootScope.$broadcast('clear.global.errors');
          }).error(function(data){

            $rootScope.$broadcast('global.error', data.errors);
          });
      },

      validate: function(user){
        return validateServiceReponseHandler.execute(
          $http.post(userApi.validateUrl, {
            email: user.email,
            userName: user.name,
            password: user.password,
            confirmPassword: user.confirmPassword
          })
        );
      }

    };
  }

]);

