@App.controller 'LoginController', ['$scope', '$http', '$location', ($scope, $http, $location) ->

  $scope.signIn = ->
    return unless $scope.loginForm.$valid

    $http(method: 'POST', url: '/sessions')
    .success (data, status) ->
      setCookie('trick_taking_dinosaurs_token', data.token, 1000 * 60 * 60 * 24)
      $location.path('/games')

]

setCookie = (name, value, validFor) ->
  document.cookie = "#{name}=#{value}; expires=#{new Date(Date.now + validFor)}"
