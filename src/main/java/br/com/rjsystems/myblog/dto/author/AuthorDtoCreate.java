package br.com.rjsystems.myblog.dto.author;

import javax.validation.constraints.NotBlank;

public class AuthorDtoCreate {

	@NotBlank
	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
