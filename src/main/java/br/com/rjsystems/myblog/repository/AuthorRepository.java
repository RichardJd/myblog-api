package br.com.rjsystems.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rjsystems.myblog.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	public boolean existsByGithubLogin(String githubLogin);
	public boolean existsByLoginEmail(String email);
	public Author findByLoginEmail(String email);
}
