package br.com.rjsystems.myblog.service;

import br.com.rjsystems.myblog.model.Author;
import br.com.rjsystems.myblog.model.Login;

public interface AuthorService {

	Author findById(Long id);
	Author save(Author author);
	Author updateGithubInformations(Long id, String githubLogin);
	Author updateLogin(Long id, Login login);
	void delete(Long id);	
}
