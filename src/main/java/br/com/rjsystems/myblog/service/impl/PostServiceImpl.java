package br.com.rjsystems.myblog.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;
import br.com.rjsystems.myblog.dto.post.PostDtoCreate;
import br.com.rjsystems.myblog.dto.post.PostDtoGet;
import br.com.rjsystems.myblog.event.ResourceCreatedEvent;
import br.com.rjsystems.myblog.repository.PostRepository;
import br.com.rjsystems.myblog.repository.filter.PostFilter;
import br.com.rjsystems.myblog.service.AuthorService;
import br.com.rjsystems.myblog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public Page<PostDtoGet> findAll(PostFilter postFilter, Pageable pageable) {
		var postsDtoGet = new ArrayList<PostDtoGet>();
		var posts = postRepository.filtrate(postFilter, pageable);

		if (!posts.isEmpty()) {
			posts.forEach(p -> {
				var authorDtoGet = AuthorDtoGet.converterToDto(p.getAuthor());
				var postDtoGet = PostDtoGet.converter(p);
				postDtoGet.setAuthor(authorDtoGet);
				postsDtoGet.add(postDtoGet);
			});
		}
		
		Page<PostDtoGet> page = new PageImpl<>(postsDtoGet, pageable, posts.getTotalElements());
		
		return page;
	}

	@Override
	public PostDtoGet findById(Long id) {
		var post = postRepository.findById(id);
		
		if (post.isPresent()) {
			var postDtoGet = PostDtoGet.converter(post.get());
			var authorDtoGet = AuthorDtoGet.converterToDto(post.get().getAuthor());
			postDtoGet.setAuthor(authorDtoGet);
			
			return postDtoGet;
		}
		return null;
	}

	@Override
	public PostDtoGet save(PostDtoCreate postDtoCreate, HttpServletResponse response) {
		var post = PostDtoCreate.converterToPost(postDtoCreate);
		post.setPublicationDate(LocalDate.now());
		
		var postSaved = postRepository.save(post);
		publisher.publishEvent(new ResourceCreatedEvent(this, postSaved.getId(), response));
		
		var postDtoGet = PostDtoGet.converter(postSaved);
		var author = authorService.findById(post.getAuthor().getId());
		postDtoGet.setAuthor(author);
		
		return postDtoGet;
	}

	@Override
	public PostDtoGet update(Long id, PostDtoCreate postDtoCreate) {
		var post = PostDtoCreate.converterToPost(postDtoCreate);
		var optionalPost = postRepository.findById(id);
		
		if(optionalPost.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		var postSaved = optionalPost.get();
		BeanUtils.copyProperties(post, postSaved, "id");
		
		postSaved.setPublicationDate(LocalDate.now());
		postSaved = postRepository.save(postSaved);
		
		var postDtoGet = PostDtoGet.converter(postSaved);
		var author = authorService.findById(post.getAuthor().getId());
		postDtoGet.setAuthor(author);
		
		return postDtoGet;
	}

	@Override
	public void delete(Long id) {
		postRepository.deleteById(id);
	}

}
