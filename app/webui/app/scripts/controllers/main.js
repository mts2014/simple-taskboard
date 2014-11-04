'use strict';

/**
 * @ngdoc function
 * @name webuiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the webuiApp
 */
angular.module('webuiApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
