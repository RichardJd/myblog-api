package br.com.rjsystems.myblog.service;

import javax.servlet.http.HttpServletResponse;

import br.com.rjsystems.myblog.dto.author.AuthorDtoCreate;
import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;
import br.com.rjsystems.myblog.model.Author;

public interface AuthorService {

	AuthorDtoGet findById(Long id);
	Author save(AuthorDtoCreate author, HttpServletResponse response);
	Author update(Long id, AuthorDtoCreate author);
	void delete(Long id);	
}
