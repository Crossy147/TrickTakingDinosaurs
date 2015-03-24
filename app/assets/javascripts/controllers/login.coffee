@App.Login.controller 'login', ($scope) ->

  $scope.signIn = ->
    # TODO change to an API request
    alert($scope.username)
