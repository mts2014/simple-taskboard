'use strict';

angular
  .module('simple-taskboard.webui.components.user')
  .directive('showUserRegisterForm', ['ngDialog', function(ngDialog){

    return {
      restrict: 'A',
      scope: {},
      replace: false,
      link: function(scope, element){
        var dialog = null;
        element.click(function(){
          dialog = ngDialog.open({
            template: 'components/user/register/user-register-form.html',
            scope: scope
          });
        });

        scope.$on('user.register.success', function(){
          dialog.close();
        });
      }
    };

  }]);
