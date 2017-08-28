import React from 'react';

class Logout extends React.Component {

    constructor(props) {
        super(props);
    }

    submitLogout = (event) => {
        event.preventDefault();
        this.props.logout();
    };

    render() {
        return (
            <div>
                <form action="" onSubmit={this.submitLogout}>
                    <input type="submit" value="Logout"/>
                </form>
            </div>
        )
    }
}

Logout.propTypes = {
    logout: React.PropTypes.func.isRequired
};

export default Logout;