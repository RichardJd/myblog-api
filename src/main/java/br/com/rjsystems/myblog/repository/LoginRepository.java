package br.com.rjsystems.myblog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rjsystems.myblog.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

	public Optional<Login> findByEmail(String email);
}
