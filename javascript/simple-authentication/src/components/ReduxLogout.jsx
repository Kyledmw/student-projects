import {connect} from 'react-redux';
import * as actions from '../actions/AccountActions';
import * as ajaxActions from '../actions/AjaxAccountActions';
import Logout from './Logout';

const mapStateToProps = (state) => {
    console.log(state);
    return {};
};

const mapDispatchToProps = (dispatch) => {
    return {
        logout: () => {
            ajaxActions.logout(() => {
                dispatch(actions.logout());
            });
        }
    }
};

const ReduxLogout = connect(mapStateToProps, mapDispatchToProps)(Logout);

export default ReduxLogout;