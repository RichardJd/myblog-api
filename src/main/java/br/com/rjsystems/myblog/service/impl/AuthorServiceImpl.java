package br.com.rjsystems.myblog.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.rjsystems.myblog.model.Author;
import br.com.rjsystems.myblog.model.Login;
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

		boolean authorExist = authorRepository.existsByGithubLogin(author.getGithubLogin());
		boolean emailExist = authorRepository.existsByLoginEmail(author.getLogin().getEmail());

		if (authorExist || emailExist) {
			throw new DataIntegrityViolationException("Author já cadastrado ou e-mail em uso");
		}

		var githubAuthor = getFromGithub.importAuthor(author.getGithubLogin());

		author.getLogin().setPassword(encriptPassword(author.getLogin().getPassword()));
		author.setAvatar(githubAuthor.getAvatar());
		author.setBiography(githubAuthor.getBiography());
		author.setName(githubAuthor.getName());
		author = authorRepository.save(author);

		return author;
	}

	@Override
	public Author updateGithubInformations(Long id, String githubLogin) {
		var author = this.findById(id);

		if (author == null) {
			return null;
		}

		var githubAuthor = getFromGithub.importAuthor(githubLogin);

		author.setGithubLogin(githubAuthor.getGithubLogin());
		author.setName(githubAuthor.getName());
		author.setAvatar(githubAuthor.getAvatar());
		author.setBiography(githubAuthor.getBiography());

		author = authorRepository.save(author);

		return author;
	}

	@Override
	public Author updateLogin(Long id, Login login) {
		var author = this.findById(id);
		if (author == null) {
			return null;
		}

		boolean isMyEmail = author.getLogin().getEmail().equals(login.getEmail());
		boolean emailExist = authorRepository.existsByLoginEmail(login.getEmail());
		if (!isMyEmail && emailExist) {
			throw new DataIntegrityViolationException("E-mail já está em uso");
		}
			
		author.getLogin().setEmail(login.getEmail());
		author.getLogin().setPassword(encriptPassword(login.getPassword()));

		authorRepository.save(author);

		return author;
	}

	@Override
	public void delete(Long id) {
		authorRepository.deleteById(id);
	}

	private String encriptPassword(String password) {
		var encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}
