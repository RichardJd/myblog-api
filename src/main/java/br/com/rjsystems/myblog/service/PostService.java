package br.com.rjsystems.myblog.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.rjsystems.myblog.dto.post.PostDtoCreate;
import br.com.rjsystems.myblog.dto.post.PostDtoGet;
import br.com.rjsystems.myblog.repository.filter.PostFilter;

public interface PostService {

	Page<PostDtoGet> findAll(PostFilter postFilter, Pageable pageable);
	PostDtoGet findById(Long id);
	PostDtoGet save(PostDtoCreate post, HttpServletResponse response);
	PostDtoGet update(Long id, PostDtoCreate post);
	void delete(Long id);
}
