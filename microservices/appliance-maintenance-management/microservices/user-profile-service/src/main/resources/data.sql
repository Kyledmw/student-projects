INSERT INTO user_profiles(account_id, first_name, surname, date_of_birth) SELECT 1, 'Manager1', 'Manager1 surname', '1960-01-01 01:01:01' WHERE NOT EXISTS(SELECT 1 from user_profiles WHERE account_id=1)

INSERT INTO user_profiles(account_id, first_name, surname, date_of_birth) SELECT 2, 'Tenant1', 'Tenant1 surname', '1990-01-01 01:01:01' WHERE NOT EXISTS(SELECT 1 from user_profiles WHERE account_id=2)

INSERT INTO user_profiles(account_id, first_name, surname, date_of_birth) SELECT 3, 'Engineer1', 'Engineer1 surname', '1965-01-01 01:01:01' WHERE NOT EXISTS(SELECT 1 from user_profiles WHERE account_id=3)
