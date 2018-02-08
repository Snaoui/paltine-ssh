package SSH.eservices.web.services.security;

import javax.annotation.PreDestroy;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InternalSecurityByPass {

	@PreDestroy
	private void destroy() {
		SecurityContextHolder.clearContext();
	}

	public void byPass() {
		SecurityContextHolder.setContext(this.createContext());
	}

	private SecurityContext createContext() {
		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
		ctx.setAuthentication(new AnonymousAuthenticationToken("key", "admin", AuthorityUtils
				.createAuthorityList("ROLE_SIMPLE_USER","ROLE_ADMIN")));
		return ctx;
	}

}
