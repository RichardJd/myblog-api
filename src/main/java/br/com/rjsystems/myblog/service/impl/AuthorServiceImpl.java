package br.com.rjsystems.myblog.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rjsystems.myblog.model.Author;
import br.com.rjsystems.myblog.repository.AuthorRepository;
import br.com.rjsystems.myblog.service.AuthorService;
import br.com.rjsystems.myblog.util.GetFromGithub;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private GetFromGithub getFromGithub;

	@Override
	public Author findById(Long id) {
		var author = authorRepository.findById(id);
		return author.isEmpty() ? null : author.get();
	}

	@Override
	public Author save(Author author, HttpServletResponse response) {
		author = getFromGithub.importAuthor(author.getLogin());
		var savedAuthor = authorRepository.save(author);

		return savedAuthor;
	}

	@Override
	public Author update(Long id, Author author) {
		author = getFromGithub.importAuthor(author.getLogin());
		var savedAuthor = getAuthor(id);
		
		savedAuthor.setLogin(author.getLogin());
		savedAuthor.setName(author.getName());
		savedAuthor.setAvatar(author.getAvatar());
		savedAuthor.setBiography(author.getBiography());
		
		savedAuthor = authorRepository.save(savedAuthor);

		return savedAuthor;
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
