@App.controller 'LoginCtrl', ($scope, $http, $location) ->

  $scope.signIn = ->
    return unless $scope.loginForm.$valid

    data =
      username: $scope.username
    $http(method: 'POST', data: data, url: '/sessions')
    .success (data, status) ->
      $location.path('/tables')
