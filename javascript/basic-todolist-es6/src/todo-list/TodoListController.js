import angular from 'angular';

class TodoListController {
    
    constructor($scope) {
        $scope.todos = [
            {task: "Create React example", author: "Kyle"},
            {task: "Create Angular2 example", author: "Kyle"}
        ];
        $scope.UI_STRINGS = {title: "Todolist"};

        $scope.submitTodo = this.submitTodo.bind(this);

        this.$scope = $scope;

        this.$inject = ['$scope'];


    }
    
    submitTodo(todo) {
        this.$scope.todos.push({task: todo.task, author: todo.author});
    };
}

export default angular.module('TODOLIST.list-ctrl', []).controller('ListCtrl', TodoListController).name;