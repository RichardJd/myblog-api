package br.com.rjsystems.myblog.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.rjsystems.myblog.model.Post;
import br.com.rjsystems.myblog.repository.filter.PostFilter;

public interface PostRepositoryQuery {

	public Page<Post> filtrate(PostFilter postFilter, Pageable pageable);
}
