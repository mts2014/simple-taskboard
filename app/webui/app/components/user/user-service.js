'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .factory('userService', ['$q', '$http', '$rootScope', 'userApi', function($q, $http, $rootScope, userApi){
   
    var currentUser = {};
    
    function filterErrorsByFields(errors, fields){
      if(fields.length === 0){
        return errors; 
      }
      var filteredErrors = [];
      angular.forEach(errors, function(error){
        angular.forEach(fields, function(field){
          if(error.fields.indexOf(field) >= 0 && filteredErrors.indexOf(error) < 0){
            filteredErrors.push(error);  
          } 
        }); 
      }); 
      return filteredErrors;
    }
    
    return {
            
      currentUser: currentUser,
          
      register: function(user){
        
        $http.post(userApi.registerUrl, 
            { 
              email: user.email, 
              name: user.name, 
              password: user.password
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
    
      validate: function(user, fields){
        var defered = $q.defer();
        $http.post('/api/users?validate', 
            { 
              email: user.email, 
              userName: user.name, 
              password: user.password
            }
          ).success(function(){
                  
            $rootScope.$broadcast('validate.success');
            defered.resolve();
          }).error(function(data){
                  
            var errors = {}; 
            var hasError = false;
            
            angular.forEach(data.errors, function(error){
              hasError = true;
              
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
            
            if(hasError) {
              $rootScope.$broadcast('validate.error', errors);
              defered.reject();
            }else{
              defered.resolve(); 
            }
            
          });
        
        return defered.promise;
      }
  
    };
  
  }]);

