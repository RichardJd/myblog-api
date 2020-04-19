package br.com.rjsystems.myblog.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;
import br.com.rjsystems.myblog.event.ResourceCreatedEvent;
import br.com.rjsystems.myblog.model.Author;
import br.com.rjsystems.myblog.repository.AuthorRepository;
import br.com.rjsystems.myblog.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public AuthorDtoGet findById(Long id) {
		var author = authorRepository.findById(id);
		if (author.isEmpty()) {
			return null;
		}
		var authorDto = importAuthorFromGithub(author.get());
		return authorDto;

	}

	@Override
	public Author save(Author author, HttpServletResponse response) {
		var savedAuthor = authorRepository.save(author);
		publisher.publishEvent(new ResourceCreatedEvent(this, savedAuthor.getId(), response));
		return savedAuthor;
	}

	@Override
	public Author update(Long id, Author author) {
		var savedAuthor = findAuthorById(id);
		BeanUtils.copyProperties(author, savedAuthor, "id");

		return authorRepository.save(savedAuthor);
	}

	@Override
	public void delete(Long id) {
		authorRepository.deleteById(id);
	}

	@Async
	@Override
	public AuthorDtoGet importAuthorFromGithub(Author author) {
		try {
			var restTamplate = new RestTemplate();
			var authorDto = restTamplate.getForObject("https://api.github.com/users/" + author.getLogin(),
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

	private Author findAuthorById(Long id) {
		var author = authorRepository.findById(id);
		if (author.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}

		return author.get();
	}
}
