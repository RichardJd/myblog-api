package br.com.rjsystems.myblog.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.rjsystems.myblog.model.Post;
import br.com.rjsystems.myblog.repository.filter.PostFilter;

public interface PostService {

	Page<Post> findAll(PostFilter postFilter, Pageable pageable);
	Post findById(Long id);
	Post save(Post post, HttpServletResponse response);
	Post update(Long id, Post post);
	void delete(Long id);
}
