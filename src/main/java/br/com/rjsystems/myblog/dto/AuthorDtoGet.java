package br.com.rjsystems.myblog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorDtoGet {

	private String login;
	
	private String name;
	
	@JsonProperty("avatar_url")
	private String avatar;
	
	@JsonProperty("bio")
	private String biography;

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
}
