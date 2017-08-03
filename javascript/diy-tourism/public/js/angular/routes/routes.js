/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * This script contains the module for controlling the routing of the application
 */
(function (window) {
    var app = angular.module('app.routes', []);

    app.config(function ($routeProvider) {

        $routeProvider.when('/', {
            controller: "InitCtrl",
            templateUrl: "public/directives/welcome-page.html"
        });

        $routeProvider.when('/event', {
            controller: "EventCtrl",
            templateUrl: "public/directives/event-page.html"
        });

        $routeProvider.when('/checkout', {
            controller: "CheckoutCtrl",
            templateUrl: "public/directives/checkout-page.html"
        });

        $routeProvider.when('/dashboard', {
            controller: "DashboardCtrl",
            templateUrl: "public/directives/dashboard.html"
        });


        $routeProvider.otherwise({
            redirectTo: '/'
        });

    });

})();
