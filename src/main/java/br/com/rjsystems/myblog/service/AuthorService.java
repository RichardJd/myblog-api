package br.com.rjsystems.myblog.service;

import javax.servlet.http.HttpServletResponse;

import br.com.rjsystems.myblog.dto.author.AuthorDtoCreate;
import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;

public interface AuthorService {

	AuthorDtoGet findById(Long id);
	AuthorDtoGet save(AuthorDtoCreate author, HttpServletResponse response);
	AuthorDtoGet update(Long id, AuthorDtoCreate author);
	void delete(Long id);	
}
