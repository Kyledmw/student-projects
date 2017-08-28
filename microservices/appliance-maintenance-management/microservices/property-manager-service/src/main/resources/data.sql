INSERT INTO property_managers(account_id) SELECT 1 WHERE NOT EXISTS(SELECT 1 FROM property_managers WHERE account_id=1)

INSERT INTO current_property_ids(manager_id, property_id) SELECT 1, 1 WHERE NOT EXISTS(SELECT 1 FROM current_property_ids WHERE property_id=1)