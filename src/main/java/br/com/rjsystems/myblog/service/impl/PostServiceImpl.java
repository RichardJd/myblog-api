package br.com.rjsystems.myblog.service.impl;

import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rjsystems.myblog.model.Post;
import br.com.rjsystems.myblog.repository.AuthorRepository;
import br.com.rjsystems.myblog.repository.PostRepository;
import br.com.rjsystems.myblog.repository.filter.PostFilter;
import br.com.rjsystems.myblog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Page<Post> findAll(PostFilter postFilter, Pageable pageable) {
		var posts = postRepository.filtrate(postFilter, pageable);		
		return posts;
	}

	@Override
	public Post findById(Long id) {
		var post = postRepository.findById(id);
		return post.isPresent() ? post.get() : null;
	}

	@Override
	public Post save(Post post, HttpServletResponse response) {
		if (!authorRepository.existsById(post.getAuthor().getId())) {
			throw new DataIntegrityViolationException("Author Id not found");
		}
		
		post.setPublicationDate(LocalDate.now());
		var postSaved = postRepository.save(post);
		return postSaved;
	}

	@Override
	public Post update(Long id, Post post) {
		var optionalPost = postRepository.findById(id);
		if(optionalPost.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		if (!authorRepository.existsById(post.getAuthor().getId())) {
			throw new DataIntegrityViolationException("Author Id not found");
		}
		
		var postSaved = optionalPost.get();
		BeanUtils.copyProperties(post, postSaved, "id");
		
		postSaved.setPublicationDate(LocalDate.now());
		postSaved = postRepository.save(postSaved);
		
		return postSaved;
	}

	@Override
	public void delete(Long id) {
		postRepository.deleteById(id);
	}
}
