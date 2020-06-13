package br.com.rjsystems.myblog.resource;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

import br.com.rjsystems.myblog.dto.author.AuthorConverter;
import br.com.rjsystems.myblog.dto.author.AuthorDtoCreate;
import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;
import br.com.rjsystems.myblog.event.ResourceCreatedEvent;
import br.com.rjsystems.myblog.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorResource {

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AuthorConverter authorConverter;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_AUTHOR') and #oauth2.hasScope('write')")
	public ResponseEntity<AuthorDtoGet> insert(@Valid @RequestBody AuthorDtoCreate authorDtoCreate, HttpServletResponse response) {
		var author = authorConverter.toEntity(authorDtoCreate);
		author = authorService.save(author, response);
		publisher.publishEvent(new ResourceCreatedEvent(this, author.getId(), response));
		
		var authorDtoGet = authorConverter.toDtoGet(author);
		return ResponseEntity.status(HttpStatus.CREATED).body(authorDtoGet);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_AUTHOR') and #oauth2.hasScope('read')")
	public ResponseEntity<AuthorDtoGet> findById(@PathVariable Long id) throws InterruptedException, ExecutionException {
		var author = authorService.findById(id);
		
		if (author != null) {
			var authorDtoGet = authorConverter.toDtoGet(author);
			return ResponseEntity.ok(authorDtoGet);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_AUTHOR') and #oauth2.hasScope('write')")
	public ResponseEntity<AuthorDtoGet> update(@PathVariable Long id, @Valid @RequestBody AuthorDtoCreate authorDtoCreate) {
		var author = authorConverter.toEntity(authorDtoCreate);
		author = authorService.update(id, author);
		
		var authorDtoGet = authorConverter.toDtoGet(author);
		return ResponseEntity.ok(authorDtoGet);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVE_AUTHOR') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		authorService.delete(id);
	}
}
