/*
 * the definition for our account module.
 */
angular.module('ngBoilerplate.account', ['ui.router', 'ngResource' /* can now inject ngResource into this module. */])
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
    .factory('accountService', function ($resource /* enables us to interface with the RESTful endpoint. */) {
        var service = {};
        service.register = function(account, success, failure) {
            var Account = $resource("/basicwebapp_war_exploded/api/v1/accounts"); // class representing the endpoint.
            /*
             * param 1: the query param field(s).
             * param 2: the data.
             * param 3: the success callback.
             * param 4: the failure callback.
             */
            Account.save({}, account, success, failure);

        };
        service.userExists = function(account, success, failure) {
            var Account = $resource("/basicwebapp_war_exploded/api/v1/accounts"); // class representing the endpoint.
            /* *
             * when we call "GET" the data is returned from the function, so the body of the request
             * is contained in the response and in the return value (in the success portion) of our response.
             */
            var data = Account.get({name: account.name}, function() {
                    var accounts = data.accounts;
                    if(accounts.length !== 0) {
                        success(accounts[0]);
                    } else {
                        failure();
                    }
                },
                failure);
        };
        return service;
    })
    .controller('LoginCtrl', function($scope, sessionService /* inject the sessionService (names must be the same) */,
                                      $state, /* inject the state service of ui.router */ accountService) {
        /*
         * this function is injectable so we inject the $scope service into it.
         * sessionService because we would want to use the name accountService on the service interacting with the RESTful
         * endpoint.
         */
        $scope.login = function() {
            accountService.userExists($scope.account, function(account) {
                sessionService.login(account);
                $state.go("home");
            }, function () {
                alert("Error loggin in user");
            });
            // alert('user logged in with ' + $scope.account.name + " and " + $scope.account.password);
        };
    })
    .controller("RegisterCtrl", function($scope, sessionService, $state, accountService) {
        $scope.register = function() {
            accountService.register($scope.account /* pass in data from our form. */,
                function(returnedData) { // call a function to get the returned data from the server.
                    sessionService.login(returnedData) /* JSON object representing the logged in account. */;
                    $state.go("home");
                },
                function() { // failure function.
                    alert("Error registering user.");
                });
            // alert('user registered with ' + $scope.account.name + " and " + $scope.account.password);
        };
    });
