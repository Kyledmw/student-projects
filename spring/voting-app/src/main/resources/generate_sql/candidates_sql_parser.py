import ast
import os
import string

print("Preparing to parse candidates.sql")

def getValuesFromCandidateSqlLine(line):
    line = string.replace(line, "INSERT INTO candidates(First_name,Second_name,Party,Constituency) VALUES ", "")
    line = string.replace(line, ";", "");
    return ast.literal_eval(line);
    
candidate_sql = open(os.path.abspath('candidates.sql'), 'r')

next_constituency_id = 1;
next_pparty_id = 1;
next_candidates_id = 1;

political_parties = {}
constituencies = {}
candidates = []

for line in candidate_sql:
    values = getValuesFromCandidateSqlLine(line)
    if values[2] not in political_parties:
        political_parties[values[2]] = next_pparty_id
        next_pparty_id += 1
    if values[3] not in constituencies:
        constituencies[values[3]] = next_constituency_id
        next_constituency_id += 1
        
    candidate = {"candidate_id": next_candidates_id, "candidate_name": values[0] + " " + values[1], "political_party_id": political_parties[values[2]], "constituency_id": constituencies[values[3]]}
    next_candidates_id += 1
    candidates.append(candidate)
    
    
candidate_sql.close()

print("File is parsed, constructing new file data.sql")

constituencies_sql_string = ''
constituencies_sql_string += "/* Constituencies */ \n\n"

constituency_votes_sql_string = ''
constituency_votes_sql_string += "/* Constituency Votes */ \n\n"

INSERT_INTO_CONSTITUENCIES = 'INSERT INTO constituencies(constituency_name) VALUES(%s)'
INSERT_INTO_CONSTITUENCY_VOTES = 'INSERT INTO constituency_votes(constituency_id, valid_votes, invalid_votes) VALUES(%s,%s,%s)'

for name in constituencies:
    constituencies_sql_string += INSERT_INTO_CONSTITUENCIES  % ("\'" + name + "\'")
    constituencies_sql_string += "\n"
    constituency_votes_sql_string += INSERT_INTO_CONSTITUENCY_VOTES % (constituencies[name], "0", "0")
    constituency_votes_sql_string += "\n"    
    
political_parties_sql_string = ''
political_parties_sql_string = "/* Political Parties */ \n\n"

INSERT_INTO_POLITICAL_PARTIES = 'INSERT INTO political_parties(political_party_name) VALUES(%s)'

for name in political_parties:
    political_parties_sql_string += INSERT_INTO_POLITICAL_PARTIES % ("\'" + name + "\'")
    political_parties_sql_string += "\n"
    
candidates_sql_string = ''
candidates_sql_string = "/* Candidates */ \n\n"

INSERT_INTO_CANDIDATES = 'INSERT INTO candidates(candidate_name,political_party_id,constituency_id) VALUES(%s,%s,%s)'
    
for candidate in candidates:
    candidates_sql_string += INSERT_INTO_CANDIDATES % (("\'" + candidate["candidate_name"] + "\'"), candidate["political_party_id"], candidate["constituency_id"])
    candidates_sql_string += "\n"

data_sql = open(os.path.abspath("../data.sql"), "w+")

data_sql.write(constituencies_sql_string + "\n\n" + political_parties_sql_string + "\n\n" + candidates_sql_string + "\n\n" + constituency_votes_sql_string)

data_sql.close()

print("File data.sql has been created");