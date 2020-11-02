package br.com.rjsystems.myblog.dto.post;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rjsystems.myblog.model.Post;

@Component
public class PostConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public PostDtoGet toDtoGet(Post post) {
		return modelMapper.map(post, PostDtoGet.class);
	}

	public Post toEntity(PostDtoCreate postDtoCreate) {
		return modelMapper.map(postDtoCreate, Post.class);
	}
}
