@App.controller 'LoginController', ['$scope', '$http', '$location', ($scope, $http, $location) ->

  $scope.signIn = ->
    return unless $scope.loginForm.$valid

    data =
      username: $scope.username
    $http(method: 'POST', data: data, url: '/sessions')
    .success (data, status) ->
      $location.path('/tables')

]

setCookie = (name, value, validFor) ->
  document.cookie = "#{name}=#{value}; expires=#{new Date(Date.now + validFor)}"
