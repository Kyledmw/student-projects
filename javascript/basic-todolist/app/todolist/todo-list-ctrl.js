(function(window) {
    
    var app = angular.module('TODOLIST.list-ctrl', []);
    
    app.controller('ListCtrl', function($scope) {
          $scope.todos = [
              {task: "Create React example", author: "Kyle"},
              {task: "Create Angular2 example", author: "Kyle"}
          ];
          
          $scope.UI_STRINGS = {title: "Todolist"};

        $scope.submitTodo = function(todo) {
            $scope.todos.push({task: todo.task, author: todo.author});
        }
    });
})();