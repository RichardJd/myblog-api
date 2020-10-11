package br.com.rjsystems.myblog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rjsystems.myblog.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	public boolean existsByGithubLogin(String githubLogin);
	public boolean existsByLoginEmail(String email);
	public Optional<Author> findByLoginEmail(String email);
}
