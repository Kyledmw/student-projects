Template.recordingData.helpers({

    recordingDataReady: function () {
        return Router.current().recordingDataHandle.ready();
    },
    recordingData: function () {
        return Recording_Data.find({device_id: Session.get(CURRENT_DEVICE_KEY)});
    }
});

Template.recording.helpers({

    getInitCameraData: function (recId) {
        var camera = Camera_Data.findOne({record_id: recId}, {sort: {createdAt: 1}});
        if (camera) {
            return "data:image/png;base64," + camera.image;
        } else {
            return "";
        }
    },
    getDate: function (timestamp) {
        var date = new Date(timestamp);
        return date.toUTCString();
    },
    validEndTime: function(end_time) {
        return (end_time > 0);
    }
});

Template.recording.events({
    'submit .js-play-footage': function () {
        event.preventDefault();

        var recId = event.target._id.value;
        var cameraData = Camera_Data.find({record_id: recId}).fetch();
        var index = 0;
        var intervalId = setInterval(function () {
            if (index == cameraData.length) {
                clearInterval(intervalId);
                if (index != 0) {
                    $('#' + recId + "Recording").attr("src", "data:image/png;base64," + cameraData[0].image);
                }
                return;
            }
            $('#' + recId + "Recording").attr("src", "data:image/png;base64," + cameraData[index].image);
            // if (index == 0) {
            //     $('#' + recId + "Recording").attr("src", "http://kia-buzz.com/wp-content/uploads/2010/08/AWD.jpg");
            // } else if (index == 1) {
            //     $('#' + recId + "Recording").attr("src", "http://www.qwed.com.pl/images/ms.jpg");
            // } else if (index == 2) {
            //     $('#' + recId + "Recording").attr("src", "http://img02.deviantart.net/ee27/i/2010/250/1/0/afamer_update_by_qwed88-d2y9mcm.jpg");
            // }
            // else {
            //     clearInterval(intervalId);
            // }
            index++;
        }, 1000);
    }
});