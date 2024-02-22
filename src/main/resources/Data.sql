
-- Als je hier je plaintext password niet meer weet, moet je een nieuw password encrypted)
-- INSERT INTO users (username, password, email, enabled) VALUES ('user', '$2a$12$IzA1Ja1LH4PSMoro9PeITO1etDlknPjSX1nLusgt1vi9c1uaEXdEK','user@test.nl', TRUE);
-- INSERT INTO users (username, password, email, enabled) VALUES ('admin', '$2a$12$IzA1Ja1LH4PSMoro9PeITO1etDlknPjSX1nLusgt1vi9c1uaEXdEK', 'admin@test.nl', TRUE);
--
INSERT INTO users (name, username, password, email, enabled) VALUES ('gebruiker', 'user', '$2a$12$IzA1Ja1LH4PSMoro9PeITO1etDlknPjSX1nLusgt1vi9c1uaEXdEK','user@test.nl', TRUE);
INSERT INTO users (name, username, password, email, enabled) VALUES ('Nick', 'Nick', '$2a$12$RSUhcbcwmPqE.B9e7xOHhe4mhs8ItRDAa/ZcPD1CJ/FoycXOSVhUC','nick@test.nl', TRUE);
INSERT INTO users (name, username, password, email, enabled) VALUES ('naamAdmin', 'admin', '$2a$12$IzA1Ja1LH4PSMoro9PeITO1etDlknPjSX1nLusgt1vi9c1uaEXdEK', 'admin@test.nl', TRUE);

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('Nick', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO activity (name, participants, teacher, date, time, activity_info) VALUES ('Yoga', 10, 'Kirstie', '2024-03-03', 'van 10u tot 11u', 'Hier komt dan de informatie te staan over de yogales.');
INSERT INTO activity (name, participants, teacher, date, time, activity_info) VALUES ('Adem coaching', 8, 'Nick', '2024-03-05', 'van 10u tot 16u', 'Hier komt dan de informatie te staan over de adem coaching.');
INSERT INTO activity (name, participants, teacher, date, time, activity_info) VALUES ('Familie opstelling', 4, 'Nick', '2024-03-06', 'van 10u tot 15u', 'Hier komt dan de informatie te staan over de familie opstellingsdag.');

INSERT INTO information (title, content, video_url) VALUES ('Titel voor content', 'hier komt dan de uitleg van een meditatie of een oefening te staan bijvoorbeeld', 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
INSERT INTO information (title, content, video_url) VALUES ('Nog een titel', 'hier komt dan de uitleg van een meditatie of een oefening te staan bijvoorbeeld', 'https://www.youtube.com/watch?v=EE-xtCF3T94');

INSERT INTO subscribe (user_id, activity_id) VALUES ('user', 1);
INSERT INTO subscribe (user_id, activity_id) VALUES ('user', 2);