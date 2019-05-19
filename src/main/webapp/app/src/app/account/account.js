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
    .controller('LoginCtrl', function($scope) {
        /*
         * this function is injectable so we inject the $scope service into it.
         */
        $scope.login = function() {
          alert('user logged in with ' + $scope.account.name + " and " + $scope.account.password);
        };
    })
    .controller("RegisterCtrl", function($scope) {
        $scope.register = function() {
            alert('user registered with ' + $scope.account.name + " and " + $scope.account.password);
        };
    });
