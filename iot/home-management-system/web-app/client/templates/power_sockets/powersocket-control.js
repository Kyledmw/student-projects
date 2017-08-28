Template.powersocketControl.helpers({

    socketsReady: function () {
        return Router.current().powerSwitchHandle.ready();
    },
    powerSockets: function () {
        return Power_Socket.find({device_id: Session.get(CURRENT_DEVICE_KEY)});
    },
    updateSocketForm: function () {
        var pSocketFeature = DeviceFeature.findOne({feature_type: "Power Sockets"});
        if (pSocketFeature) {
            var pSocketImpl = DeviceFeature_Implementation.findOne({
                device_id: Session.get(CURRENT_DEVICE_KEY),
                device_feature_id: pSocketFeature._id
            });
            if (pSocketImpl) {
                var power_sockets = Power_Socket.find({device_id: Session.get(CURRENT_DEVICE_KEY)}).fetch();
                if (power_sockets) {
                    power_sockets.forEach(function (element) {
                        var state = (element.power_switch == 1);
                        $('#' + element._id + 'SocketSwitch').prop('checked', state);
                    });
                }
                $('#addPSocketName').prop('disabled', false);
                $('#addPSocketNumber').prop('disabled', false);
                $('#addPSocketSubmit').prop('disabled', false);
            } else {
                $('#addPSocketName').prop('disabled', true);
                $('#addPSocketNumber').prop('disabled', true);
                $('#addPSocketSubmit').prop('disabled', true);
            }
        }
    }
});

Template.powersocketControl.rendered = function () {
    var pSocketFeature = DeviceFeature.findOne({feature_type: "Power Sockets"});
    if (pSocketFeature) {
        var pSocketImpl = DeviceFeature_Implementation.findOne({
            device_id: Session.get(CURRENT_DEVICE_KEY),
            device_feature_id: pSocketFeature._id
        });
        if (pSocketImpl) {
            $('#addPSocketName').prop('disabled', false);
            $('#addPSocketNumber').prop('disabled', false);
            $('#addPSocketSubmit').prop('disabled', false);
        } else {
            $('#addPSocketName').prop('disabled', true);
            $('#addPSocketNumber').prop('disabled', true);
            $('#addPSocketSubmit').prop('disabled', true);
        }
    }
};

Template.powerSocket.rendered = function () {
    var power_sockets = Power_Socket.find({device_id: Session.get(CURRENT_DEVICE_KEY)}).fetch();
    if (power_sockets) {
        power_sockets.forEach(function (element) {
            var state = (element.power_switch == 1);
            $('#' + element._id + 'SocketSwitch').prop('checked', state);
        });
    }
};

Template.powersocketControl.events({
    'click .js-remove-socket': function (event) {
        var a = event.currentTarget;
        Power_Socket.remove($(a).val());
    },
    'click .js-switch-socket': function (event) {
        var a = event.currentTarget;
        var pSwitch = $(a).is(":checked") ? 1 : 0;
        Power_Socket.update($(a).val(), {$set: {power_switch: pSwitch}});
    },
    'submit .js-new-socket': function (event) {
        event.preventDefault();
        var name = event.target.powersocket_name.value;
        var socketNumber = parseInt(event.target.powersocket_number.value);
        var socket = Power_Socket.findOne({device_id: Session.get(CURRENT_DEVICE_KEY), socket_number: socketNumber});
        if (socket) {
            $('#socketErrModal').modal('toggle');
        }
        else
        {
            if(isNaN(socketNumber)) {
                $('#sockNumberErrModal').modal('toggle');
            } else if(name == "") {
                $('#sockNameErrModal').modal('toggle');
            } else {
                Meteor.modelFactories.createPowerSocket(Session.get(CURRENT_DEVICE_KEY), name, socketNumber, 0);
            }
        }
    }
});