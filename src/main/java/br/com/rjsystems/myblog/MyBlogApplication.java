package br.com.rjsystems.myblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.rjsystems.myblog.config.property.MyBlogProperty;

@SpringBootApplication
@EnableConfigurationProperties(MyBlogProperty.class)
public class MyBlogApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MyBlogApplication.class, args);
	}

}
