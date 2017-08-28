INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 1, 'OKAY', 'Message', 'http://placehold.it/32x32', 'COMMON' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=1)
INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 2, 'REPAIRING', 'Message', 'http://placehold.it/32x32', 'COMMON' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=2)
INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 3, 'IRREPAIRABLE', 'Message', 'http://placehold.it/32x32', 'COMMON' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=3)
INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 4, 'MALFUNCTION', 'Message', 'http://placehold.it/32x32', 'COMMON' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=4)

INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 5, 'LEAK', 'Message', 'http://placehold.it/32x32', 'DISH_WASHER' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=5)
INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 6, 'PUMP_FAULT', 'Message', 'http://placehold.it/32x32', 'DISH_WASHER' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=6)

INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 7, 'NOT_COOLING', 'Message', 'http://placehold.it/32x32', 'FRIDGE_FREEZER' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=7)
INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 8, 'LIGHT_FAULT', 'Message', 'http://placehold.it/32x32', 'FRIDGE_FREEZER' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=8)

INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 9, 'WONT_SPIN', 'Message', 'http://placehold.it/32x32', 'MICROWAVE' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=9)
INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 10, 'NOT_HEATING', 'Message', 'http://placehold.it/32x32', 'MICROWAVE' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=10)

INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 11, 'DOOR_WONT_CLOSE', 'Message', 'http://placehold.it/32x32', 'OVEN' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=11)
INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 12, 'NOT_HEATING', 'Message', 'http://placehold.it/32x32', 'OVEN' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=12)

INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 13, 'WONT_SPIN', 'Message', 'http://placehold.it/32x32', 'TUMBLE_DRYER' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=13)
INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 14, 'NOT_HEATING', 'Message', 'http://placehold.it/32x32', 'TUMBLE_DRYER' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=14)

INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 15, 'PUMP_FAULT', 'Message', 'http://placehold.it/32x32', 'WASHING_MACHINE' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=15)
INSERT INTO appliance_statuses(id, status_type, message, icon_url, appliance_type) SELECT 16, 'NOT_HEATING', 'Message', 'http://placehold.it/32x32', 'WASHING_MACHINE' WHERE NOT EXISTS(SELECT 1 FROM appliance_statuses WHERE id=16)


