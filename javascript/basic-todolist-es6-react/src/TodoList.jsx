import React from 'react';

const UI_STRINGS = {
    title: "Todolist"
};

class TodoList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {todos: [
            {id: 1, task: "Create Angular1 example", author: "Kyle"},
            {id: 2, task: "Create Angular2 example", author: "Kyle"}
        ]};
    }

    renderTasks = () => {
        return this.state.todos.map((todo) => {
            return <li>{todo.author}: {todo.task}</li>
        });
    };

    submitTodo = (event) => {
        event.preventDefault();
        var todos = this.state.todos;
        todos.push({task: event.target.task.value, author: event.target.author.value});
        this.setState({todos: todos});
    };

    render() {
        return (
            <div>
                <h1>{UI_STRINGS.title}</h1>
                <ul>
                    {this.renderTasks()}
                </ul>
                <form name="todoForm" onSubmit={this.submitTodo}>
                    <input name="author"  type="text" placeholder="Author" />
                    <input name="task" type="text" placeholder="Task"/>
                    <input type="submit" />
                </form>
            </div>
        );
    }
}

export default TodoList;