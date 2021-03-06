'use strict';

angular
  .module('simple-taskboard.webui.components.api', [])
  .constant('userApi', {
    registerUrl: '/api/users',
    validateUrl: '/api/users?validate'
  })
  .constant('authApi', {
    authenticateUrl: '/api/authenticate',
    validateUrl:     '/api/authenticate?validate'
  });
