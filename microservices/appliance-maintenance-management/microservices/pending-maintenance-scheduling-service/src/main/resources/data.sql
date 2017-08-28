INSERT INTO pending_maintenance_schedules(id, repair_report_id, start_time, end_time, scheduler_type, schedule_status) SELECT 1, 1, '2017-01-01 01:01:01', '2017-01-01 01:01:01', 'ENGINEER_REPRESENTITIVE', 'ACCEPTED' WHERE NOT EXISTS(SELECT 1 FROM pending_maintenance_schedules WHERE id=1)
INSERT INTO pending_maintenance_schedules(id, repair_report_id, start_time, end_time, scheduler_type, schedule_status) SELECT 2, 3, '2017-01-01 01:01:01', '2017-01-01 01:01:01', 'ENGINEER_REPRESENTITIVE', 'PENDING' WHERE NOT EXISTS(SELECT 1 FROM pending_maintenance_schedules WHERE id=2)
INSERT INTO pending_maintenance_schedules(id, repair_report_id, start_time, end_time, scheduler_type, schedule_status) SELECT 3, 4, '2017-01-01 01:01:01', '2017-01-01 01:01:01', 'PROPERTY_REPRESENTITIVE', 'ACCEPTED' WHERE NOT EXISTS(SELECT 1 FROM pending_maintenance_schedules WHERE id=3)
INSERT INTO pending_maintenance_schedules(id, repair_report_id, start_time, end_time, scheduler_type, schedule_status) SELECT 4, 3, '2017-01-01 01:01:01', '2017-01-01 01:01:01', 'PROPERTY_REPRESENTITIVE', 'PENDING' WHERE NOT EXISTS(SELECT 1 FROM pending_maintenance_schedules WHERE id=4)
