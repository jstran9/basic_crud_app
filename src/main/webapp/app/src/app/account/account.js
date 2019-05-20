/*
 * the definition for our account module.
 */
angular.module('ngBoilerplate.account', ['ui.router'])
    /*
     * the config function is called when the application is bootstrapped.
     * custom services can be injected into this function.
     * $stateProvider is a service defined the ui.router module.
     */
    .config(function($stateProvider) {
        $stateProvider.state('login', {
            url: '/login', // url associated with the state.
            views: { // specify the views we'd like to place.
                /*
                 * in ui-router all states have a parent except the root unnamed states.
                 * login state does not have a defined parent and it has the root unnamed state.
                 * the root unnamed state's template is the root unnamed template and this can be found in
                 * index.html where the ui-view directive is specified.
                 */
                'main': {
                    templateUrl: 'account/login.tpl.html',
                    controller: 'LoginCtrl'
                }
            },
            data: { pageTitle : 'Login'}
        })
        .state('register', {
            url: '/register', // url associated with the state.
            views: {
                'main': {
                    templateUrl: 'account/register.tpl.html',
                    controller: 'RegisterCtrl'
                }
            },
            data: { pageTitle : 'Registration'}
        });
    })
    .factory('sessionService', function () {
        // first argument is the service name.
        var session = {};
        session.login =  function(data) {
            alert('user logged in with credentials ' + data.name + " and " + data.password);
            /*
             * this function will manage if the user is logged in or not.
             * this is not a secure function.
             * localStorage allows us to persistent data on the browser.
             */
          localStorage.setItem("session", data); // persist data between page refreshes.
        };
        session.logout = function () {
            // just remove the contents stored in the login function.
            localStorage.removeItem("session");
        };
        session.isLoggedIn = function() {
            // checks if the user is logged in.
            return localStorage.getItem("session") !== null;
        };
        return session; // return the service.
    })
    .controller('LoginCtrl', function($scope, sessionService /* inject the sessionService (names must be the same) */,
                                      $state /* inject the state service of ui.router */) {
        /*
         * this function is injectable so we inject the $scope service into it.
         * sessionService because we would want to use the name accountService on the service interacting with the RESTful
         * endpoint.
         */
        $scope.login = function() {
            sessionService.login($scope.account);
            $state.go("home");
          // alert('user logged in with ' + $scope.account.name + " and " + $scope.account.password);
        };
    })
    .controller("RegisterCtrl", function($scope, sessionService, $state) {
        $scope.register = function() {
            sessionService.login($scope.account);
            $state.go("home");
            // alert('user registered with ' + $scope.account.name + " and " + $scope.account.password);
        };
    });
