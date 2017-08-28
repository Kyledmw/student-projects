import * as types from './ActionTypes.js';

export function login(token) {
    return {
        type: types.LOGIN,
        token: token
    };
}

export function register(token) {
    return {
        type: types.REGISTER,
        token: token
    }
}

export function logout() {
    return {
        type: types.LOGOUT
    }
}