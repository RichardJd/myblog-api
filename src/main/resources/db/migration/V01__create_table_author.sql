CREATE TABLE author (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	login VARCHAR(100) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO author (login) VALUES ('RichardJd');