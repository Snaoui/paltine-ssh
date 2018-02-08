package SSH.eservices.web.services.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Custom user extends spring one
 */
public class CustomUser
		extends User {

	private static final long serialVersionUID = 1;

	private AuthentMode authentMode;

	/**
	 * Default constructor
	 *
	 * @param username
	 * @param password
	 * @param authorities
	 * @param authentMode
	 */
	public CustomUser(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities, AuthentMode authentMode) {
		super(username, password, enabled, true, true, true, authorities);
		this.authentMode = authentMode;
	}

	public AuthentMode getAuthentMode() {
		return authentMode;
	}

	public void setAuthentMode(AuthentMode authentMode) {
		this.authentMode = authentMode;
	}

}

