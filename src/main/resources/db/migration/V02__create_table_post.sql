CREATE TABLE post (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	topic VARCHAR(50) NOT NULL,
	subtopic VARCHAR(100) NOT NULL,
	text TEXT NOT NULL,
	publication_date DATE,
	id_author BIGINT NOT NULL,
	FOREIGN KEY (id_author) REFERENCES author (id)
)ENGINE InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO post (title, topic, subtopic, text, publication_date, id_author) 
VALUES("First Post", "Java - Spring Boot", "Application Java with Spring Boot", "Body of Post", DATE(NOW()), 1);