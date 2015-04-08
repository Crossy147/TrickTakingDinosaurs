@App.config ($routeProvider, $locationProvider) ->
  $routeProvider
  .when '/login',
    templateUrl: 'assets/templates/login.html'
    controller: 'LoginController'
  .when '/tables',
    templateUrl: 'assets/templates/tables.html'
    controller: 'TablesController'
  .otherwise
    redirectTo: '/login'

  $locationProvider.html5Mode(enabled: false, requireBase: false).hashPrefix('!')