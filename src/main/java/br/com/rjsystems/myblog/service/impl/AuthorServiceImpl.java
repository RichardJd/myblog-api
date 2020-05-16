package br.com.rjsystems.myblog.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rjsystems.myblog.dto.author.AuthorDtoCreate;
import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;
import br.com.rjsystems.myblog.event.ResourceCreatedEvent;
import br.com.rjsystems.myblog.model.Author;
import br.com.rjsystems.myblog.repository.AuthorRepository;
import br.com.rjsystems.myblog.service.AuthorService;
import br.com.rjsystems.myblog.util.GetFromGithub;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private GetFromGithub getFromGithub;

	@Override
	public AuthorDtoGet findById(Long id) {
		var author = authorRepository.findById(id);
		if (author.isEmpty()) {
			return null;
		}

		var authorDto = AuthorDtoGet.converterToDto(author.get());
		return authorDto;
	}

	@Override
	public AuthorDtoGet save(AuthorDtoCreate authorDtoCreate, HttpServletResponse response) {
		var authorDtoGet = getFromGithub.importAuthor(authorDtoCreate);
		var author = AuthorDtoGet.convertToAuthor(authorDtoGet);

		var savedAuthor = authorRepository.save(author);
		publisher.publishEvent(new ResourceCreatedEvent(this, savedAuthor.getId(), response));

		return AuthorDtoGet.converterToDto(savedAuthor);
	}

	@Override
	public AuthorDtoGet update(Long id, AuthorDtoCreate authorDtoCreate) {
		var authorDtoGet = getFromGithub.importAuthor(authorDtoCreate);
		var savedAuthor = getAuthor(id);
		
		savedAuthor.setLogin(authorDtoCreate.getLogin());
		savedAuthor.setName(authorDtoGet.getName());
		savedAuthor.setAvatar(authorDtoGet.getAvatar());
		savedAuthor.setBiography(authorDtoGet.getBiography());
		
		savedAuthor = authorRepository.save(savedAuthor);

		return AuthorDtoGet.converterToDto(savedAuthor);
	}

	@Override
	public void delete(Long id) {
		authorRepository.deleteById(id);
	}

	private Author getAuthor(Long id) {
		var author = authorRepository.findById(id);
		if (author.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}

		return author.get();
	}
}
