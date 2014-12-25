'use strict';

describe('validate directive', function(){
        
  it('changeイベントでサービスの呼び出し', function(){
    var userService = jasmine.createSpyObj('userService', ['validate']);
    module('simple-taskboard.webui.components.user', function($provide){
      $provide.value('userService', userService);
    });
    
    var $compile, $rootScope;
    inject(function(_$compile_, _$rootScope_){
      $compile = _$compile_;
      $rootScope = _$rootScope_;
    });
            
    
    var scope = $rootScope.$new();
    scope.email = 'taro@test';
    
    var input = $compile('<input type="text" ng-model="email" user-validate>')(scope);
    input.change();
    
    expect(userService.validate).toHaveBeenCalledWith(jasmine.objectContaining({ email: "taro@test"}), ['email']);
    
  }); 
        
});
