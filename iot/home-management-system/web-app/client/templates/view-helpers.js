Meteor.viewHelpers = {
    getType: function(typeId) {
        if (typeId) {
            var type = Measurement_Type.findOne({ _id: typeId });
            return type.type;
        }
    }
}
