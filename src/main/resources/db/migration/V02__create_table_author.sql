CREATE TABLE author (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	github_login VARCHAR(100) NOT NULL,
	name VARCHAR(255),
	avatar VARCHAR(255),
	biography TEXT,
	id_login BIGINT NOT NULL,
	FOREIGN KEY (id_login) REFERENCES login (id)
)ENGINE InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO author (github_login, id_login) VALUES ('RichardJd', 1);
