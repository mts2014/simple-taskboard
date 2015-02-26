'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .factory('userService', ['$q', '$http', '$rootScope', 'userApi', function($q, $http, $rootScope, userApi){
   
    var currentUser = {};
    
    return {
            
      currentUser: currentUser,
          
      register: function(user){
        
        $http.post(userApi.registerUrl, 
            { 
              email: user.email, 
              userName: user.name, 
              password: user.password,
              confirmPassword: user.confirmPassword
            }
          ).success(function(){
            jQuery.extend(currentUser, user);
            
            $rootScope.$broadcast('currentuser.changed'); 
            $rootScope.$broadcast('user.register.success'); 
            $rootScope.$broadcast('clear.global.errors'); 
          }).error(function(data){
            
            $rootScope.$broadcast('global.error', data.errors); 
          });
      }, 
    
      validate: function(user){
        var defered = $q.defer();
        
        $http.post('/api/users?validate', 
            { 
              email: user.email, 
              userName: user.name, 
              password: user.password,
              confirmPassword: user.confirmPassword
            }
          ).success(function(){
                  
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

