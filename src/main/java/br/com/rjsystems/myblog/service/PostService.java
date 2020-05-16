package br.com.rjsystems.myblog.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.rjsystems.myblog.dto.post.PostDtoCreate;
import br.com.rjsystems.myblog.dto.post.PostDtoGet;

public interface PostService {

	List<PostDtoGet> findAll();
	PostDtoGet findById(Long id);
	PostDtoGet save(PostDtoCreate post, HttpServletResponse response);
	PostDtoGet update(Long id, PostDtoCreate post);
	void delete(Long id);
}
