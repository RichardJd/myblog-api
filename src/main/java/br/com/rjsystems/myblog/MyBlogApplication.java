package br.com.rjsystems.myblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyBlogApplication {
	
	private static Logger logger = LoggerFactory.getLogger(MyBlogApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando a API MyBlog");
		SpringApplication.run(MyBlogApplication.class, args);
		logger.info("API MyBlog pronta para receber requisições");
	}

}
