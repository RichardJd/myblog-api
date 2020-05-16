package br.com.rjsystems.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rjsystems.myblog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
