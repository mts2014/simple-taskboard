'use strict';

describe('userRegister directive', function(){

  var $compile, $rootScope, userService;

  beforeEach(function(){
    module('simple-taskboard.webui.components.user');

    inject(function(_$compile_, _$rootScope_, _userService_){
      $compile = _$compile_;
      $rootScope = _$rootScope_;
      userService = _userService_;
    });

    spyOn(userService, 'register');
  });

  it('clickしてユーザ登録処理が呼ばれること', function(){

    var scope = $rootScope.$new();
    scope.email = 'taro@test';
    scope.userName = 'taro';
    scope.password = 'pass';

    var element = $compile('<button user-register></button>')(scope);
    element.click();

    expect(userService.register)
      .toHaveBeenCalledWith({ email: 'taro@test', name: 'taro', password: 'pass', confirmPassword: 'pass' });

  });
});
