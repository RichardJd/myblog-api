package br.com.rjsystems.myblog.dto.author;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.rjsystems.myblog.dto.login.LoginDtoCreate;

public class AuthorDtoCreate {

	@NotBlank
	@Size(max = 100)
	private String githubLogin;

	@Valid
	private LoginDtoCreate login;

	public String getGithubLogin() {
		return githubLogin;
	}

	public void setGithubLogin(String login) {
		this.githubLogin = login;
	}

	public LoginDtoCreate getLogin() {
		return login;
	}

	public void setLogin(LoginDtoCreate login) {
		this.login = login;
	}
}
