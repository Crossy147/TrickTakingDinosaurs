@App.controller 'GameCtrl', ($scope, $http, $location) ->
  $scope.game = []

  $scope.deal = () ->
    $http(method: 'GET', url: '/game/deal')
    .success (data, status) -> 
      drawHand('HORIZONTAL', 'southCanvas', data)
      drawHand('HORIZONTAL', 'northCanvas', 13)
      drawHand('VERTICAL', 'westCanvas', 13)
      drawHand('VERTICAL', 'eastCanvas', 13)
]
