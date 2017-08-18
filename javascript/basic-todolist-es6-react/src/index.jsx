import React from 'react';
import ReactDOM from 'react-dom';

import TodoList from './TodoList';

class Index extends React.Component {
    render() {
        return (
            <div>
                <TodoList />
            </div>
        );
    }
}


ReactDOM.render(<Index/>, document.getElementById("render-target"));