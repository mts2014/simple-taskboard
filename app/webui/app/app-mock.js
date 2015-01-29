'use strict';

angular
  .module('simple-taskboard.webui.mock', ['ngMockE2E', 'ngStorage'])
  .run(function ($httpBackend) {
    
    // $httpBackend.whenPOST('/api/users?validate').respond(function(method, url, data){
    //   var validateData = angular.fromJson(data); 
    //   
    //   if(validateData.user.email === 'hoge@webui.ut'){
    //     return [400, { errors: [
    //       {
    //         developperMessage: '',
    //         userMessage: '指定されたメールアドレスはすでに登録されています。',
    //         errorCode: 'e001',
    //         fields: ['email']
    //       }         
    //     ]}]; 
    //   }
    //   
    //   return [200, {}];
    // });
    
    $httpBackend.whenPOST(/.*/).passThrough();
    $httpBackend.whenPUT(/.*/).passThrough();
    $httpBackend.whenDELETE(/.*/).passThrough();
    $httpBackend.whenGET(/.*/).passThrough();
    
  });
