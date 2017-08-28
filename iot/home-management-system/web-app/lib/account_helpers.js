/**
 ********************************************************************
 * This script contains functions used throughout the applications.
 * These contain functionality associated with accounts
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
Meteor.accountHelpers = {

    /**
     * Check if the given user is an admin.
     * 
     * If no userId provided it will check the current user
     * 
     * @param userId, id for a user to check if user is an admin
     * @return boolean 
     */
    isAdmin: function (userId) {
        try {
            var a_account_level = Account_Level.findOne({ 'level': ADMIN });
            if(userId) {
                var user = Meteor.users.findOne({_id: userId});
                return (user.profile.account_level_id === a_account_level._id);
            } else {
                if(Accounts.user() && a_account_level) {
                	    return (Accounts.user().profile.account_level_id === a_account_level._id);
                } else {
                    return false;
                }
            }
        } catch (err) {
            console.log("Error occurred in isAdmin: " + err);
            return false;
        }
    },

    /**
     * Check is the current user is a home owner
     * 
     * @return boolean
     */
    isHomeOwner: function () {
        try {
            var ho_account_level = Account_Level.findOne({ 'level': HOME_OWNER });
            if(Accounts.user() && ho_account_level) {
            	    return (Accounts.user().profile.account_level_id === ho_account_level._id);
            } else {
                return false;
            }
        } catch (err) {
            console.log("Error occurred in isHomeOwner: " + err);
            return false;
        }
    }
};