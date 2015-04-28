@App.config ($routeProvider, $locationProvider) ->
  $routeProvider
  .when '/login',
    templateUrl: 'assets/templates/login.html'
    controller: 'LoginCtrl'
  .when '/tables',
    templateUrl: 'assets/templates/tables.html'
    controller: 'TablesCtrl'
  .when '/tables/:id',
    templateUrl: 'assets/templates/game.html'
    controller: 'GameCtrl'
  .otherwise
    redirectTo: '/login'

  $locationProvider.html5Mode(enabled: false, requireBase: false).hashPrefix('!')
