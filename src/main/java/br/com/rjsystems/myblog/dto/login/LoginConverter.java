package br.com.rjsystems.myblog.dto.login;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rjsystems.myblog.model.Login;

@Component
public class LoginConverter {

	@Autowired
	private ModelMapper modelMapper;

	public Login toEntity(LoginDtoCreate loginDtoCreate) {
		return modelMapper.map(loginDtoCreate, Login.class);
	}
}
