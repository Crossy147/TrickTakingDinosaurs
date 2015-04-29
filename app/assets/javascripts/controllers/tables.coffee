@App.controller 'TablesCtrl', ($scope, $http, $location) ->
  $scope.tables = []

  $http(method: 'GET', url: '/tables')
  .success (data, status) ->
    $scope.tables = data

  $scope.joinGame = (id) ->
    $location.path "/tables/#{id}"

  $scope.createGame = () ->
    return unless $scope.gameForm.$valid

    data =
      table_name: $scope.tableName

    $http(method: 'POST', url: '/tables', data: data)
    .success (data, status) ->
      tableId = data.id
      $location.path "/tables/#{tableId}"
