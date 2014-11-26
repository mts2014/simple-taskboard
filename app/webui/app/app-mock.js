'use strict';

angular
  .module('simple-taskboard.webui.mock', ['ngMockE2E'])
  .run(function ($httpBackend) {
          
    $httpBackend.whenPOST('/api/users').respond(200, {});
    
    $httpBackend.whenGET(/.*/).passThrough();
    
  });
