(function(window) {
    var app = angular.module('myApp.controllers', []);
    var URL_PREFIX = 'http://ADMIN-PORTFOLIO-SIMPLE-CMS/';
    // create the controller and inject Angular's $scope
    app.controller('MainCtrl', function($scope, $http) {

        $scope.currentYear = new Date().getFullYear();
        $scope.contact = "Click to display email!";
        $scope.error = false;
        $scope.aboutme = {};
        $scope.workExp = [];
        $scope.skills = [];
        $scope.form_result = "";
        $scope.form_success = "";

        $http({method: 'GET', url: URL_PREFIX + '/rest/aboutme', cache: true}).
                success(function(aboutme_data, status)
                {
                    $scope.aboutme = aboutme_data;
                }).
                error(function(aboutme_data, status)
                {
                    console.log('Status: ' + status);
                    console.log('Aboutme_Data: ' + aboutme_data);
                });

        $http({method: 'GET', url: URL_PREFIX + '/rest/skills', cache: true}).
                success(function(skill_data, status)
                {
                    $scope.skills = skill_data;
                }).
                error(function(skill_data, status)
                {
                    console.log('Status: ' + status);
                    console.log('Skills_Data: ' + skill_data);
                });

        $http({method: 'GET', url: URL_PREFIX + '/rest/experience', cache: true}).
                success(function(exp_data, status)
                {
                    $scope.workExp = exp_data;
                }).
                error(function(exp_data, status)
                {
                    console.log('Status: ' + status);
                    console.log('Experience_Data: ' + exp_data);
                });

        $scope.submitContactForm = function(message) {
            $scope.form_result = "";
            $scope.form_success = "";
            
            $http.post(URL_PREFIX + '/rest/messages', {
                name: message.name,
                email: message.email,
                content: message.content
            }).error(function(data, status)
            {
                $scope.form_success = "alert alert-danger";
                $scope.form_result = "Form submit failed";
                console.log('status: ' + status);
                console.log('data: ' + data);
            }).success(function(data, status) {
                
                console.log('data: ' +  data);
                console.log('status: ' + status);
                
                $scope.form_success = "alert alert-success";
                $scope.form_result = "Message successfully sent";
               
            });
        };


        $scope.emailVisible = false;

        $scope.toggleEmail = function() {
            $scope.contact = "YOUR@EMAIL.HERE";
        };
    });
})();