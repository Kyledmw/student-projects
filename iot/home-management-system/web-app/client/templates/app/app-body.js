Template.appBody.helpers({
  // We use #each on an array of one item so that the "list" template is
  // removed and a new copy is added when changing lists, which is
  // important for animation purposes. #each looks at the _id property of it's
  // items to know when to insert a new item and when to update an old one.
  thisArray: function() {
    return [this];
  },
  emailLocalPart: function() {
      if(Meteor.user()) {
        var email = Meteor.user().emails[0].address;
        return email.substring(0, email.indexOf('@'));
      }
  }
});

Template.appBody.events({

  'click .js-logout': function() {
    Meteor.logout(function(err) {
        Router.go('home');
    });
  }
});
