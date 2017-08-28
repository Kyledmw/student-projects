import {connect} from 'react-redux';
import * as actions from '../actions/AccountActions';
import * as ajaxActions from '../actions/AjaxAccountActions';
import Register from './Register';

const mapStateToProps = (state) => {
    console.log(state);
    return {};
};

const mapDispatchToProps = (dispatch) => {
    return {
        register: (userDetails) => {
            ajaxActions.register(userDetails, (data) => {
                if(data !== null) {
                    dispatch(actions.register(data.token));
                }
            });
        }
    }
};

const ReduxRegister = connect(mapStateToProps, mapDispatchToProps)(Register);

export default ReduxRegister;