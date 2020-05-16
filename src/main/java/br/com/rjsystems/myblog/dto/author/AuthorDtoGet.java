package br.com.rjsystems.myblog.dto.author;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.rjsystems.myblog.model.Author;

public class AuthorDtoGet {

	private String login;
	
	private String name;
	
	@JsonProperty("avatar_url")
	private String avatar;
	
	@JsonProperty("bio")
	private String biography;
	
	public AuthorDtoGet() {}
	
	public AuthorDtoGet(Author author) {
		this.login = author.getLogin();
		this.name = author.getName();
		this.avatar = author.getAvatar();
		this.biography = author.getBiography();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public static AuthorDtoGet converterToDto(Author author) {
		return new AuthorDtoGet(author);
	}
	
	public static Author convertToAuthor(AuthorDtoGet authorDtoGet) {
		var author = new Author();
		author.setLogin(authorDtoGet.getLogin());
		author.setName(authorDtoGet.getName());
		author.setAvatar(authorDtoGet.getAvatar());
		author.setBiography(authorDtoGet.getBiography());
		return author;
	}
}
