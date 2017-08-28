Template.realtimeCamera.helpers({

    cameraReady: function () {
        return Router.current().cameraDataHandle.ready();
    },
    image: function() {
        var camera = Camera_Data.findOne({ device_id: Session.get(CURRENT_DEVICE_KEY) }, { sort: { createdAt: -1 } });
        if (camera) {
            return "data:image/png;base64," + camera.image;
        } else {
            return "";
        }
    }
});
