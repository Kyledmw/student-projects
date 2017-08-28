import React from 'react';

class Login extends React.Component {

    constructor(props) {
        super(props);
    }

    submitLogin = (event) => {
        event.preventDefault();
        var userDetails = {username: event.target.username.value, password: event.target.password.value};

        this.props.login(userDetails);
    };

    render() {
        return (
            <div>
                <form action="" onSubmit={this.submitLogin}>
                    <input type="text" name="username" placeholder="Username: " />
                    <input type="password" name="password" placeholder="Password: " />
                    <input type="submit" />
                </form>
            </div>
        )
    }
}

Login.propTypes = {
    login: React.PropTypes.func.isRequired
};

export default Login;