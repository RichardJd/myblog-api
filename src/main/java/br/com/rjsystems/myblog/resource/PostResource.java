package br.com.rjsystems.myblog.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.rjsystems.myblog.dto.post.PostDtoCreate;
import br.com.rjsystems.myblog.dto.post.PostDtoGet;
import br.com.rjsystems.myblog.repository.filter.PostFilter;
import br.com.rjsystems.myblog.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@GetMapping
	public Page<PostDtoGet> findAll(PostFilter postFilter, Pageable pageable) {
		return postService.findAll(postFilter, pageable);
	}

	@PostMapping
	public ResponseEntity<PostDtoGet> insert(@Valid @RequestBody PostDtoCreate post, HttpServletResponse response) {
		PostDtoGet postSaved = postService.save(post, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(postSaved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDtoGet> findById(@PathVariable Long id) {
		var postDtoGet = postService.findById(id);
		return postDtoGet != null ? ResponseEntity.ok(postDtoGet) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDtoGet> update(@PathVariable Long id, @Valid @RequestBody PostDtoCreate post) {
		var postSaved = postService.update(id, post);
		return ResponseEntity.ok(postSaved);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		postService.delete(id);
	}
}
