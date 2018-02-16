package SSH.eservices.web.services.security;

import java.io.IOException;
import java.io.PrintWriter;

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
		setRealmName("SSHPlatine");
	}

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws
			IOException {
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 - " + authException.getMessage());
	}

	public void afterPropertiesSet() throws Exception {
		setRealmName("SSHPlatine");
		super.afterPropertiesSet();
	}

}
