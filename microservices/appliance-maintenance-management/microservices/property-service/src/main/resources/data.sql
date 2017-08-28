INSERT INTO addresses(id, address_line_1, address_line_2, city, state, country, postal_code, longitude, latitude) SELECT 1, 'House Name', 'Street Name', 'City', 'County', 'Country', '0000', 0, 0 WHERE NOT EXISTS(SELECT 1 FROM addresses WHERE id=1)

INSERT INTO properties(id, address_id, age, bed_count, bathroom_count) SELECT 1, 1, 20, 5, 2 WHERE NOT EXISTS(SELECT 1 from properties WHERE id=1) 