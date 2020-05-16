package br.com.rjsystems.myblog.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.rjsystems.myblog.dto.author.AuthorDtoCreate;
import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;

@Component
public class GetFromGithub {

	@Async
	public AuthorDtoGet importAuthor(AuthorDtoCreate authorDtoCreate) {
		try {
			var restTamplate = new RestTemplate();
			var authorDto = restTamplate.getForObject("https://api.github.com/users/" + authorDtoCreate.getLogin(),
					AuthorDtoGet.class);

			authorDto = CompletableFuture.completedFuture(authorDto).get();

			return authorDto;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
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
