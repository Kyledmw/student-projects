/**
 ********************************************************************
 * This script contains publish functions for account 
 * and associated data collection.
 * They can be subscribed to by ddp clients
 * 
 * Some subscriptions require a user to be loggedin and or an admin
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */

/**
 * Publication to allows admin users to query users
 * 
 * @return Users collection or null
 */
Meteor.publish('accounts', function () {
    console.log('Accounts Collection Request');
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        console.log("User is an admin, subscribing to users");
        return Meteor.users.find({}, { fields: { 'password': 0 } });
    } else {
        console.log("User is not an admin. returning null");
        return null;
    }
});

/**
 * Publication which subscribes to the account levels collection
 * 
 * @return collection of account levels
 */
Meteor.publish('account_levels', function () {
    console.log("Account Level Collection Request");
    return Account_Level.find();
});

/**
 * Publication which subscribes to the monitoringlevels collection
 * 
 * @return collection of monitoring levels
 */
Meteor.publish('monitoring_levels', function () {
    console.log("Monitoring Level Collection Request");
    return Monitoring_Level.find();
});

/**
 * Publication which subscribes to the user with the given id
 * 
 * An admin must be logged in
 * 
 * @param accountId id of the account to find
 * @return user for the given id or null
 */
Meteor.publish('account', function (accountId) {
    console.log("Received account subscription for account: " + accountId);
    check(accountId, String);
    if(Meteor.accountHelpers.isAdmin(this.userId)) {
        console.log("User is an admin, subscribing to user");
        return Meteor.users.find({ _id: accountId }, { fields: { 'password': 0 } });
    } else {
        console.log("User is not an admin. returning null");
        return null;
    }
});