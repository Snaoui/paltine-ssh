package SSH.eservices.web.services.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider
		implements AuthenticationProvider {

	@Autowired
	CustomUserDetailsService userService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		logger.debug("Trying to authenticate user with email [{}] and password [{}]", username, password);

		UserDetails user = userService.loadUserByUsername(username);

		if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
			throw new BadCredentialsException("User with provided email [" + username + "] not found !");
		}

		if (password == null || !password.equals(user.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}

		logger.info("Successful authentication!");
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (GrantedAuthority authority : user.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(String.valueOf(authority)));
		}

		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(
				UsernamePasswordAuthenticationToken.class);
	}
}