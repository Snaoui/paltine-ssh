package SSH.eservices.web.services.security;

import SSH.eservices.model.User;
import SSH.eservices.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class CustomUserDetailsService
		implements UserDetailsService {

	private static final Logger LOGGER = getLogger(CustomUserDetailsService.class);

	@Autowired
	public InternalSecurityByPass internalSecurityByPass;

	@Autowired
	public UserRepository userRepository;

	public CustomUserDetailsService() {
		super();
	}


	/**
	 * Load user from database
	 *
	 * @param email : The user name
	 * @param authType : The authentication type
	 * @return The loaded user
	 * @throws UsernameNotFoundException : if user not found
	 */
	@Transactional
	public UserDetails loadUserByUsername(String email, AuthentMode authType) throws UsernameNotFoundException {

		String lowerCaseUserName = email.toLowerCase();

		User user = userRepository.findByEmail(email);
		if (user == null) {
			LOGGER.error("User '{}' not found", email);
			throw new UsernameNotFoundException("User " + email + " not found");
		}
		LOGGER.info("User '{}' loaded", email);

		List<String> roles = new ArrayList<>();
		roles.add("ROLE_SIMPLE_USER");
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			internalSecurityByPass.byPass();
		}

		return new CustomUser(lowerCaseUserName, user.getPassword(), true,
				AuthorityUtils.createAuthorityList(roles.toArray(new String[0])), authType);
	}

	/**
	 * @param email : The user email
	 * @return The loaded user
	 * @throws UsernameNotFoundException : if user not found
	 * @see CustomUserDetailsService#loadUserByUsername(String, AuthentMode)
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return loadUserByUsername(email, AuthentMode.LOGIN);
	}
}
