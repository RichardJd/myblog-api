package br.com.rjsystems.myblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.rjsystems.myblog.dto.AuthorDtoGet;
import br.com.rjsystems.myblog.model.Author;
import br.com.rjsystems.myblog.repository.AuthorRepository;
import br.com.rjsystems.myblog.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public AuthorDtoGet findById(Long id) {
		var author = authorRepository.findById(id);
		if (author.isEmpty())
			return null;

		var restTamplate = new RestTemplate();
		var authorDto = restTamplate.getForObject("https://api.github.com/users/" + author.get().getLogin(),
				AuthorDtoGet.class);

		return authorDto;
	}

	@Override
	public Author save(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Author update(Long id, Author author) {
		// TODO Auto-generated method stub
		return null;
	}
}
