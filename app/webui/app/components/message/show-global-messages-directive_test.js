'use strict';

describe('showGlobalMsgs directive', function(){
  
  it('global.errorイベントでエラーメッセージが表示できること', function(){
    
    module('simple-taskboard.webui.components.message');
    var $rootScope, $compile;
    inject(function(_$compile_, _$rootScope_){
      $compile = _$compile_;
      $rootScope = _$rootScope_;
    });
    
    var scope = $rootScope.$new();
    var element = $compile('<div show-global-msgs></div>')(scope);
    
    $rootScope.$broadcast('global.error', [{ userMessage: 'hoge' }]);
    
    expect(scope.msg).toEqual('hoge');
    
  });
});
