@App.config ($routeProvider, $locationProvider) ->
  $routeProvider
  .when '/login',
    templateUrl: 'login.html'
    controller: 'LoginController'
  .when '/tables',
    templateUrl: 'tables.html'
    controller: 'TablesController'
  .otherwise
    redirectTo: '/login'

  $locationProvider.html5Mode(enabled: false, requireBase: false).hashPrefix('!')