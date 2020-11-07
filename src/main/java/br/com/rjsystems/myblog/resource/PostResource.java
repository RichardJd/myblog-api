package br.com.rjsystems.myblog.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rjsystems.myblog.dto.post.PostConverter;
import br.com.rjsystems.myblog.dto.post.PostDtoCreate;
import br.com.rjsystems.myblog.dto.post.PostDtoGet;
import br.com.rjsystems.myblog.event.ResourceCreatedEvent;
import br.com.rjsystems.myblog.model.Post;
import br.com.rjsystems.myblog.repository.filter.PostFilter;
import br.com.rjsystems.myblog.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostResource {

	private static Logger logger = LoggerFactory.getLogger(PostResource.class);
	
	@Autowired
	private PostService postService;

	@Autowired
	private PostConverter postConverter;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<PostDtoGet> findAll(PostFilter postFilter, Pageable pageable) {
		Page<Post> posts = postService.findAll(postFilter, pageable);
		Page<PostDtoGet> postsDto = posts.map(post -> postConverter.toDtoGet(post));

		return postsDto;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_POST') and #oauth2.hasScope('write')")
	public ResponseEntity<PostDtoGet> insert(@Valid @RequestBody PostDtoCreate postDtoCreate,
			HttpServletResponse response) {

		logger.info("Iniciando inserção de postagem...");
		var post = postConverter.toEntity(postDtoCreate);
		post = postService.save(post);
		publisher.publishEvent(new ResourceCreatedEvent(this, post.getId(), response));

		var postDtoGet = postConverter.toDtoGet(post);
		logger.info("Finalizando inserção de postagem...");
		return ResponseEntity.status(HttpStatus.CREATED).body(postDtoGet);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDtoGet> findById(@PathVariable Long id) {
		var post = postService.findById(id);
		if (post == null) {
			return ResponseEntity.notFound().build();
		}

		var postDtoGet = postConverter.toDtoGet(post);
		return ResponseEntity.ok(postDtoGet);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_POST') and #oauth2.hasScope('write')")
	public ResponseEntity<PostDtoGet> update(@PathVariable Long id, @Valid @RequestBody PostDtoCreate postDtoCreate) {

		logger.info("Iniciando atualização de postagem...");
		var post = postConverter.toEntity(postDtoCreate);
		post = postService.update(id, post);

		var postDtoGet = postConverter.toDtoGet(post);
		logger.info("Finalizando inserção de postagem...");
		return ResponseEntity.ok(postDtoGet);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVE_POST') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		logger.info("Iniciando remoção de postagem...");
		postService.delete(id);
		logger.info("Finalizando remoção de postagem...");
	}
}
