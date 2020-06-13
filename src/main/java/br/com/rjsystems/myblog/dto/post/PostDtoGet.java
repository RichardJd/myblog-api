package br.com.rjsystems.myblog.dto.post;

import java.time.LocalDate;

import br.com.rjsystems.myblog.dto.author.AuthorDtoGet;

public class PostDtoGet {

	private Long id;
	private String title;
	private String topic;
	private String subtopic;
	private String text;
	private LocalDate publicationDate;
	private AuthorDtoGet author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public AuthorDtoGet getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDtoGet author) {
		this.author = author;
	}
}
