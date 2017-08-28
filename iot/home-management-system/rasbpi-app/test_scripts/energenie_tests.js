var energenie = require('energenie');

energenie.switchOn(1, function() {
    energenie.switchOff(1);
});

energenie.switchOn(2, function() {
    energenie.switchOff(2);
});3