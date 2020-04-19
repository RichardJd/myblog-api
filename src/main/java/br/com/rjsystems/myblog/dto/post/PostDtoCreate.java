package br.com.rjsystems.myblog.dto.post;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.rjsystems.myblog.model.Post;

public class PostDtoCreate {

	@NotBlank
	@Size(max = 50)
	private String title;

	@Size(max = 50)
	private String topic;

	@Size(max = 100)
	private String subtopic;

	@NotBlank
	@Column(columnDefinition = "text")
	private String text;

	@NotNull
	private Long authorId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getSubtopic() {
		return subtopic;
	}

	public void setSubtopic(String subtopic) {
		this.subtopic = subtopic;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
	public static Post converterToPost(PostDtoCreate postDtoCreate) {
		Post post = new Post();
		post.setTitle(postDtoCreate.getTitle());
		post.setTopic(postDtoCreate.getTopic());
		post.setSubtopic(postDtoCreate.getSubtopic());
		post.setText(postDtoCreate.getText());
		post.getAuthor().setId(postDtoCreate.getAuthorId());
		return post;
	}
}
