package br.com.rjsystems.myblog.dto.author;

import javax.validation.constraints.NotBlank;

public class GithubDtoCreate {

	@NotBlank
	private String githubLogin;

	public String getGithubLogin() {
		return githubLogin;
	}

	public void setGithubLogin(String githubLogin) {
		this.githubLogin = githubLogin;
	}
}
