/**
 ********************************************************************
 * This script contains ddp push functions for accounts to be used by ddp clients
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */

Meteor.methods({
    
    /**
     * Request the server to send a reset password email
     * 
     * @return success or error
     */
	'pushPasswordReset': function(email) {
		console.log("Received a password reset request for email: " + email);
        check(email, String);
        var account = Accounts.findUserByEmail(email);
        if(account) {
            console.log("Account exists, sending reset password email");
            Accounts.sendResetPasswordEmail(account._id);
            return "success";
        } else {
            return "error";
        }
	}
	
});