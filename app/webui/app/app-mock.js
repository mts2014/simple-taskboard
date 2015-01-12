'use strict';

angular
  .module('simple-taskboard.webui.mock', ['ngMockE2E', 'ngStorage'])
  .run(function ($httpBackend, $sessionStorage) {
    
    var $storage = $sessionStorage.$default({ users: [] });
    
    var users = (function(){
      return {
        add: function(user){
          $storage.users.push(user); 
        },
        has: function(user){
          var _users = $storage.users;
          for(var i=0; i < _users.length; i++){
            if(_users[i].email === user.email) {
              return true;
            }
          }
          return false;
        }
      }; 
    })();
    
    var errors = function(userMsg, errorCode, fields, devMsg){
      return {
        errors: [{
          developerMessage: devMsg,
          userMessage: userMsg,
          errorCode: errorCode,
          fields: fields 
        }] 
      }; 
    };
    
    $httpBackend.whenPOST('/api/users').respond(function(method, url, data){
      var newUser = angular.fromJson(data); 
      
      if(users.has(newUser)){
        return [400, errors( 
          '指定されたメールアドレスはすでに登録されています。', 'e0001', ['email'] 
        )]; 
      }
      
      users.add(newUser);
      return [200, {}];
    });
    
    $httpBackend.whenPOST('/api/users/validate').respond(function(method, url, data){
      var validateData = angular.fromJson(data); 
      
      if(!validateData.user.email) {
        return [400, errors( 
          'メールアドレスは必須です。', 'e0001', ['email'] 
        )]; 
      }
      
      return [200, {}];
    });
    
    $httpBackend.whenGET(/.*/).passThrough();
    
  });
