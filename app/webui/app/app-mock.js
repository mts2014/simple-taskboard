'use strict';

angular
  .module('simple-taskboard.webui.mock', ['ngMockE2E', 'ngStorage'])
  .run(function ($httpBackend) {
    
    $httpBackend.whenPOST('/api/users/validate').respond(function(method, url, data){
      var validateData = angular.fromJson(data); 
      
      if(validateData.user.email === 'hoge@test.jp'){
        return [403, { errors: [
          {
            developperMessage: '',
            userMessage: '指定されたメールアドレスはすでに登録されています。',
            errorCode: 'e001',
            fields: ['email']
          }         
        ]}]; 
      }
      
      return [200, {}];
    });
    
    $httpBackend.whenGET(/.*/).passThrough();
    
  });
