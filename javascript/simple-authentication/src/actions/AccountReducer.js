import * as types from './ActionTypes';

const initialState = {
    token: null
};

export const AccountReducer = (state = initialState, action) => {
    switch(action.type) {
        case types.LOGIN:
            return {
                token: action.token
            };
        case types.REGISTER:
            return {
                token: action.token
            };
        case types.LOGOUT:
            return {
                token: null
            };
        default:
            return {
                token: state.token
            }
    }
};