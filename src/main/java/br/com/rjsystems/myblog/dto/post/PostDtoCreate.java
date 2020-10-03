package br.com.rjsystems.myblog.dto.post;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
}
