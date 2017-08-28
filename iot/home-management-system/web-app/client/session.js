CURRENT_ACCOUNT_KEY = "current_account";
CURRENT_DEVICE_KEY = 'current_device';
Meteor.startup(function () {
    Session.setDefault(CURRENT_ACCOUNT_KEY , '');
    Session.setDefault(CURRENT_DEVICE_KEY, '');
});
