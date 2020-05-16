package br.com.rjsystems.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rjsystems.myblog.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	public Author findByLogin(String login);
}
