package br.com.rjsystems.myblog.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.rjsystems.myblog.dto.author.AuthorConverter;
import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;
import br.com.rjsystems.myblog.model.Author;

@Component
public class GetFromGithub {
	
	private static Logger logger = LoggerFactory.getLogger(GetFromGithub.class);
	
	@Autowired 
	private AuthorConverter authorConverter;

	@Async
	public Author importAuthor(String login) {
		try {
			logger.info("Iniciando a busca por informações da conta do Github...");
			var restTamplate = new RestTemplate();
			var authorDto = restTamplate.getForObject("https://api.github.com/users/" + login,
					AuthorDtoGet.class);

			authorDto = CompletableFuture.completedFuture(authorDto).get();

			logger.info("Finalizando a busca por informações da conta do Github...");
			return authorConverter.toEntity(authorDto);
		} catch (InterruptedException e) {
			logger.error("Erro ao buscar por informações da conta do Github: " + e.getMessage());
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
			logger.error("Erro ao buscar por informações da conta do Github: " + e.getMessage());
		}
		return null;
	}
	
//	private void updateFromGithub() {
//		var authors = authorRepository.findAll();
//		authors.forEach(a -> {
//			AuthorDtoGet authorDtoGet = importAuthorFromGithub(a);
//			this.update(a.getId(), AuthorDtoGet.convertToAuthor(authorDtoGet));
//		});
//	}
}
