/**
 ********************************************************************
 * This script seeds the mongodb with initial users if none exist
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
Meteor.mongoSeeders.account = function () {
      
      //Check if there are no users in the db
      if (Meteor.users.find().count() === 0) {
          
          var full = Monitoring_Level.findOne({level: FULL_MONITORING});
          var self = Monitoring_Level.findOne({level: 'self_monitoring'});
          var monitorer = Monitoring_Level.findOne({level: 'monitorer'});
            
            //List of accounts to seed database with
            var accountData = [
                  { email: "test1@test.com", password: "123456", monitoring_id: full._id},
                  { email: "test2@test.com", password: "123456", monitoring_id: full._id},
                  { email: "test3@test.com", password: "123456", monitoring_id: full._id},
                  { email: "test4@test.com", password: "123456", monitoring_id: full._id},
            ];
            
            //Create initial admin account
            var adminLevel = Account_Level.findOne({level: ADMIN});
            var homeownerLevel = Account_Level.findOne({level: HOME_OWNER});
            
            Meteor.modelFactories.createAccount('admin@hms.com', '123456', adminLevel._id, monitorer._id);
            
            //Loop through account list and create account models
            _.each(accountData, function (data) {
                Meteor.modelFactories.createAccount(data.email, data.password, homeownerLevel._id, data.monitoring_id)
            });
      }
};
