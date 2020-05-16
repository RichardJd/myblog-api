package br.com.rjsystems.myblog.resource;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rjsystems.myblog.dto.author.AuthorDtoCreate;
import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;
import br.com.rjsystems.myblog.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorResource {

	@Autowired
	private AuthorService authorService;

	@PostMapping
	public ResponseEntity<AuthorDtoGet> insert(@Valid @RequestBody AuthorDtoCreate authorDtoCreate, HttpServletResponse response) {
		AuthorDtoGet authorSaved = authorService.save(authorDtoCreate, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(authorSaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuthorDtoGet> findById(@PathVariable Long id) throws InterruptedException, ExecutionException {
		AuthorDtoGet author = authorService.findById(id);
		return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AuthorDtoGet> update(@PathVariable Long id, @Valid @RequestBody AuthorDtoCreate authorDtoCreate) {
		AuthorDtoGet savedAuthor = authorService.update(id, authorDtoCreate);
		return ResponseEntity.ok(savedAuthor);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		authorService.delete(id);
	}
}
