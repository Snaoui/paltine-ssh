package SSH.eservices.web.services.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Service;

@Service
public class CustomBasicAuthenticationEntryPoint
		extends BasicAuthenticationEntryPoint {

	/**
	 * @param realmName : The real name
	 */
	public CustomBasicAuthenticationEntryPoint(String realmName) {
		setRealmName(realmName);
	}

	/**
	 * Default constructor
	 */
	public CustomBasicAuthenticationEntryPoint() {
		setRealmName("test");
	}

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws
			IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}

}
