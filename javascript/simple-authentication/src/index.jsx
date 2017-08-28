import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from  'react-redux';
import {createStore, combineReducers} from 'redux';

import {AccountReducer} from './account/actions/AccountReducer';

import ReduxLogin from './account/components/ReduxLogin';
import ReduxRegister from './account/components/ReduxRegister';
import ReduxLogout from './account/components/ReduxLogout';

const reducers = combineReducers({
    accountState: AccountReducer
});

const store = createStore(reducers);

class Index extends React.Component {
    render() {
        return (
            <Provider store={store}>
                <div>
                    <h1>Register</h1>
                    <ReduxRegister />
                    <h1>Login</h1>
                    <ReduxLogin />
                    <h1>Logout</h1>
                    <ReduxLogout />
                </div>
            </Provider>
        )
    }
}

ReactDOM.render(<Index />, document.getElementById("render-target"));
