/* Users */


INSERT INTO users(user_name, password, email, credit_limit) VALUES('kyle', 'password', 'kyle.williamson@mycit.ie', 1000)
INSERT INTO users(user_name, password, email, credit_limit) VALUES('joel', 'password', 'joelis.satkauskas@mycit.ie', 1000)
INSERT INTO users(user_name, password, email, credit_limit) VALUES('alan', 'password', 'asheehan1@mycit.ie', 1000)


/* Projects */


INSERT INTO projects(user_id, proj_name, description, yt_vid_code, goal_amount, finish_time, cancelled, completed, image_name) VALUES(1, 'Appliance Maintence Scheduling', 'Description', 'tJKAQggSs9Q', 10000, '2016-12-25 23:00:00.000', false, true, '1_project.jpg')
INSERT INTO projects(user_id, proj_name, description, yt_vid_code, goal_amount, finish_time, cancelled, completed, image_name) VALUES(2, 'Receipts and stuff', 'Description', 'tJKAQggSs9Q', 10000, '2016-12-25 23:00:00.000', true, false, '2_project.jpg')
INSERT INTO projects(user_id, proj_name, description, yt_vid_code, goal_amount, finish_time, cancelled, completed, image_name) VALUES(3, 'Sensors and all that', 'Description', 'tJKAQggSs9Q', 10000, '2016-12-25 23:00:00.000', false, false, '3_project.jpg')


/* Pledges */

INSERT INTO pledges(active_pledge, amount_pledged, user_id, project_id) VALUES(true, 300, 1, 1)
INSERT INTO pledges(active_pledge, amount_pledged, user_id, project_id) VALUES(true, 400, 1, 1)
INSERT INTO pledges(active_pledge, amount_pledged, user_id, project_id) VALUES(true, 200, 2, 2)


/* Roles */

INSERT INTO role(name) VALUES('User')