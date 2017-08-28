import React from 'react';

class Register extends React.Component {

    constructor(props) {
        super(props);
    }

    submitRegister = (event) => {
        event.preventDefault();
        var userDetails = {username: event.target.username.value, password: event.target.password.value};

        this.props.register(userDetails);
    };

    render() {
        return (
            <div>
                <form action="" onSubmit={this.submitRegister} >
                    <input type="text" name="username" placeholder="Username: " />
                    <input type="password" name="password" placeholder="Password: " />
                    <input type="submit" />
                </form>
            </div>
        )
    }
}

Register.propTypes = {
    register: React.PropTypes.func.isRequired
};

export default Register;