package br.com.rjsystems.myblog.dto.author;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorDtoGet {

	private Long id;

	@JsonProperty("login")
	private String githubLogin;

	private String name;

	@JsonProperty("avatar_url")
	private String avatar;

	@JsonProperty("bio")
	private String biography;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGithubLogin() {
		return githubLogin;
	}

	public void setGithubLogin(String githubLogin) {
		this.githubLogin = githubLogin;
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
