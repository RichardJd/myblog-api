package br.com.rjsystems.myblog.dto.author;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rjsystems.myblog.model.Author;

@Component
public class AuthorConverter {
	
	@Autowired 
	private ModelMapper modelMapper;

	public AuthorDtoGet toDtoGet(Author author) {
		return modelMapper.map(author, AuthorDtoGet.class);
	}
	
	public List<AuthorDtoGet> toCollectionDtoGet(List<Author> authors) {
		return authors.stream()
				.map(author -> toDtoGet(author))
				.collect(Collectors.toList());
	}
	
	public Author toEntity(AuthorDtoCreate authorDtoCreate) {
		return modelMapper.map(authorDtoCreate, Author.class);
	}
	
	public Author toEntity(AuthorDtoGet authorDtoGet) {
		return modelMapper.map(authorDtoGet, Author.class);
	}
}
