CREATE TABLE login (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE permition (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE login_permition (
	id_login BIGINT NOT NULL,
	id_permition BIGINT NOT NULL,
	PRIMARY KEY (id_login, id_permition),
	FOREIGN KEY (id_login) REFERENCES login(id),
	FOREIGN KEY (id_permition) REFERENCES permition(id)
)ENGINE InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO login (name, email, password) VALUES ('Admin', 'admin@admin.com', '$2a$10$fQfTcbzAyoCdOpCwyBJFhejPCE7z4TurFviOdOgKs16xUMz5b49GO');

INSERT INTO permition (id, description) VALUES (1, 'ROLE_REGISTER_AUTHOR');
INSERT INTO permition (id, description) VALUES (2, 'ROLE_REMOVE_AUTHOR');
INSERT INTO permition (id, description) VALUES (3, 'ROLE_SEARCH_AUTHOR');

INSERT INTO permition (id, description) VALUES (4, 'ROLE_REGISTER_POST');
INSERT INTO permition (id, description) VALUES (5, 'ROLE_DELETE_POST');

-- Admin
INSERT INTO login_permition (id_login, id_permition) VALUES (1, 1);
INSERT INTO login_permition (id_login, id_permition) VALUES (1, 2);
INSERT INTO login_permition (id_login, id_permition) VALUES (1, 3);
INSERT INTO login_permition (id_login, id_permition) VALUES (1, 4);
INSERT INTO login_permition (id_login, id_permition) VALUES (1, 5);