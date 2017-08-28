import {connect} from 'react-redux';
import * as actions from '../actions/AccountActions';
import * as ajaxActions from '../actions/AjaxAccountActions';
import Login from './Login';

const mapStateToProps = (state) => {
    console.log(state);
    return {};
};

const mapDispatchToProps = (dispatch) => {
    return {
        login: (userDetails) => {
            ajaxActions.login(userDetails, (data) => {
                if(data !== null) {
                    dispatch(actions.login(data.token));
                }
            });
        }
    }
};

const ReduxLogin = connect(mapStateToProps, mapDispatchToProps)(Login);

export default ReduxLogin;