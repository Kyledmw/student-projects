INSERT INTO citizens(name, salary) VALUES ('Lyndon Ceballos', 7000);
INSERT INTO citizens(name, salary) VALUES ('Lori Restivo', 10000);
INSERT INTO citizens(name, salary) VALUES ('Pamula Lona', 20000);
INSERT INTO citizens(name, salary) VALUES ('Kenny Randel', 25000);
INSERT INTO citizens(name, salary) VALUES ('Bruno Kiernan', 40000);

INSERT INTO taxation(tax_from, amount_eligable_for_tax, tax_percent, tax_limited) VALUES(10000, 15000, 0.2, TRUE);
INSERT INTO taxation(tax_from, amount_eligable_for_tax, tax_percent, tax_limited) VALUES(25000, 0, 0.4, FALSE);
INSERT INTO taxation(tax_from, amount_eligable_for_tax, tax_percent, tax_limited) VALUES(0, 0, 0.03, FALSE);
