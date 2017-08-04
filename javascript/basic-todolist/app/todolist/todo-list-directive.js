(function(window) {
    var app = angular.module('TODOLIST.directives-list', []);
    
    app.directive('todoList', function() {
       return {
           restrict: 'E',
           templateUrl: 'app/todolist/todo-list.html'
       } ;
    });
})();