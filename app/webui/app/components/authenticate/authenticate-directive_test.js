'use strict';

describe('authenticate directive', function(){

  it('clickしたときに、AuthServiceが呼び出されること', function(){
    var authServiceMock = jasmine.createSpyObj('authService', ['authenticate']);

    module('simple-taskboard.webui.components.authenticate', function($provide){
      $provide.value('authService', authServiceMock);
    });

    var $compile, $rootScope;
    inject(function(_$compile_, _$rootScope_){
      $compile = _$compile_;
      $rootScope = _$rootScope_;
    });

    var scope = $rootScope.$new();
    scope.loginId='test';
    scope.password='pass';
    var button = $compile('<button authenticate></button>')(scope);

    button.click();

    expect(authServiceMock.authenticate).toHaveBeenCalledWith('test', 'pass');
  });

});
