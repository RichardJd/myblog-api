package br.com.rjsystems.myblog.service;

import javax.servlet.http.HttpServletResponse;

import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;
import br.com.rjsystems.myblog.model.Author;

public interface AuthorService {

	AuthorDtoGet findById(Long id);
	Author save(Author author, HttpServletResponse response);
	Author update(Long id, Author author);
	void delete(Long id);
	
	AuthorDtoGet importAuthorFromGithub(Author author);
}
