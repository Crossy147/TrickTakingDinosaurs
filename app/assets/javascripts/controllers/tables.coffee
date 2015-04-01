@App.controller 'TablesController', ['$scope', '$http', '$location', ($scope, $http, $location) ->
  $scope.tables = []

  $http(method: 'GET', url: '/tables.json')
  .success (data, status) ->
    $scope.tables = data

  $scope.joinGame = (id) ->
    $http(method: 'PUT', url: "/tables/#{id}")
    .success (data, status) ->
      $location.path "tables/#{id}"

  $scope.createGame = () ->
    return unless $scope.gameForm.$valid

    data =
      table_name: $scope.tableName

    $http(method: 'POST', url: '/tables', data: data)
    .success (data, status) ->
      id = data.id
      $location.path "tables/#{id}"
]