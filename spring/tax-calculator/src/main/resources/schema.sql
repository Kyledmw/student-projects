CREATE TABLE citizens(
	citizen_id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(40),
	salary DECIMAL	
);

CREATE TABLE taxation(
	taxation_id INT PRIMARY KEY AUTO_INCREMENT,
	tax_from DECIMAL,
	amount_eligable_for_tax DECIMAL,
	tax_percent DOUBLE,
	tax_limited BOOLEAN
);