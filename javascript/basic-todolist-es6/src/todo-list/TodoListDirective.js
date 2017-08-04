import angular from 'angular';
import todoList from './todo-list.html';

var TodoListDirective = function() {
    return {
        restrict: 'E',
        template: todoList
    }
};

export default angular.module('TODOLIST.directives-list', []).directive('todoList', TodoListDirective).name;