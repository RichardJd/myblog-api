package br.com.rjsystems.myblog.dto.author;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDtoCreate {

	@NotBlank
	@Size(max = 100)
	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
