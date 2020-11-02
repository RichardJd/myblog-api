INSERT INTO permition (id, description) VALUES (1, 'ROLE_REGISTER_AUTHOR');
INSERT INTO permition (id, description) VALUES (2, 'ROLE_REMOVE_AUTHOR');
INSERT INTO permition (id, description) VALUES (3, 'ROLE_SEARCH_AUTHOR');

INSERT INTO permition (id, description) VALUES (4, 'ROLE_REGISTER_POST');
INSERT INTO permition (id, description) VALUES (5, 'ROLE_REMOVE_POST');

-- Admin
INSERT INTO login_permition (id_login, id_permition) VALUES (1, 1);
INSERT INTO login_permition (id_login, id_permition) VALUES (1, 2);
INSERT INTO login_permition (id_login, id_permition) VALUES (1, 3);
INSERT INTO login_permition (id_login, id_permition) VALUES (1, 4);
INSERT INTO login_permition (id_login, id_permition) VALUES (1, 5);
