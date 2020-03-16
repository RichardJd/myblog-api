package br.com.rjsystems.myblog.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rjsystems.myblog.dto.AuthorDtoGet;
import br.com.rjsystems.myblog.model.Author;
import br.com.rjsystems.myblog.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorResource {

	@Autowired
	private AuthorService authorService;

	@GetMapping("/{id}")
	public ResponseEntity<AuthorDtoGet> getAuthor(@PathVariable Long id) {
		AuthorDtoGet author = authorService.findById(id);
		return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Author> insertAuthor(@Valid @RequestBody Author author) {
		Author authorSaved = authorService.save(author);
		return ResponseEntity.status(HttpStatus.CREATED).body(authorSaved);
	}
}
