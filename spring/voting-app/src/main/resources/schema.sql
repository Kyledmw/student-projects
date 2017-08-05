CREATE TABLE candidates(
	candidate_id INT PRIMARY KEY AUTO_INCREMENT,
	candidate_name VARCHAR(40),
	political_party_id INT,
	constituency_id INT
);

CREATE TABLE political_parties(
	political_party_id INT PRIMARY KEY AUTO_INCREMENT,
	political_party_name VARCHAR(40)
);

CREATE TABLE constituencies(
	constituency_id INT PRIMARY KEY AUTO_INCREMENT,
	constituency_name VARCHAR(40)
);

CREATE TABLE candidate_votes(
	candidate_votes_id INT PRIMARY KEY AUTO_INCREMENT,
	candidate_id INT,
	vote_rank INT
);

CREATE TABLE constituency_votes(
	constituency_votes_id INT PRIMARY KEY AUTO_INCREMENT,
	constituency_id INT,
	valid_votes INT,
	invalid_votes INT
);


