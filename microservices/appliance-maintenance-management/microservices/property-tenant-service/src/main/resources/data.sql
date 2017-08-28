INSERT INTO property_tenants(account_id, current_property_id, residence_status) SELECT 2, 1, 'OCCUPANT' WHERE NOT EXISTS(SELECT 1 FROM property_tenants WHERE account_id=2)
