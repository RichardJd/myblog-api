package br.com.rjsystems.myblog.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rjsystems.myblog.model.Login;
import br.com.rjsystems.myblog.repository.LoginRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Login> loginOptional = loginRepository.findByEmail(email);

		Login login = loginOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new User(email, login.getPassword(), getPermitions(login));
	}

	private Collection<? extends GrantedAuthority> getPermitions(Login login) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		login.getPermitions()
				.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));

		return authorities;
	}

}
