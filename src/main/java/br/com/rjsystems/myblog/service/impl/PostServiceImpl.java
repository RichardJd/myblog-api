package br.com.rjsystems.myblog.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import br.com.rjsystems.myblog.dto.post.PostDtoCreate;
import br.com.rjsystems.myblog.dto.post.PostDtoGet;
import br.com.rjsystems.myblog.event.ResourceCreatedEvent;
import br.com.rjsystems.myblog.repository.PostRepository;
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
	public List<PostDtoGet> findAll() {
		var postsDtoGets = new ArrayList<PostDtoGet>();
		var posts = postRepository.findAll();

		if (!posts.isEmpty()) {
			posts.forEach(p -> {
				var authorDtoGet = authorService.importAuthorFromGithub(p.getAuthor());
				var postDtoGet = PostDtoGet.converter(p);
				postDtoGet.setAuthor(authorDtoGet);
				postsDtoGets.add(postDtoGet);
			});
		}
		return postsDtoGets;
	}

	@Override
	public PostDtoGet findById(Long id) {
		var post = postRepository.findById(id);
		
		if (post.isPresent()) {
			var authorDtoGet = authorService.importAuthorFromGithub(post.get().getAuthor());
			var postDtoGet = PostDtoGet.converter(post.get());
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
	public PostDtoGet update(Long id, PostDtoCreate post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
