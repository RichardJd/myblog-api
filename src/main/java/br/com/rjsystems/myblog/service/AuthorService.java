package br.com.rjsystems.myblog.service;

import br.com.rjsystems.myblog.dto.AuthorDtoGet;
import br.com.rjsystems.myblog.model.Author;

public interface AuthorService {

	AuthorDtoGet findById(Long id);
	Author save(Author author);
	Author update(Long id, Author author);
}
