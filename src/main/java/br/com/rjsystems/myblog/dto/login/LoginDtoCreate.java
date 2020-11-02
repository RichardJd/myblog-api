package br.com.rjsystems.myblog.dto.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDtoCreate {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Size(min = 6, max = 150)
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
