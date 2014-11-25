'use strict';

describe('userRegister directive', function(){
        
  it('clickしてユーザ登録処理が呼ばれること', function(){
    
    module('simple-taskboard.webui.components.user'); 
    
    var $compile, $rootScope;
    inject(function(_$compile_, _$rootScope_){
      $compile = _$compile_;
      $rootScope = _$rootScope_;
    });
    
    var element = $compile('<button user-register></button>')($rootScope);
    element.click(); 
    expect($rootScope.clicked).toBe(true);
    
  });
});
