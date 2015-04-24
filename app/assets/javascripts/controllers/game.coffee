@App.controller 'GameCtrl', ($scope, $http, $location) ->
  $scope.game = []

  $scope.deal = () ->
    $http(method: 'GET', url: '/game/deal', data: '')
]
