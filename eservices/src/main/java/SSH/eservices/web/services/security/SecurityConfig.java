package SSH.eservices.web.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		/*auth.inMemoryAuthentication()
				.withUser("user")  // #1
				.password("password")
				.roles("USER")
				.and()
				.withUser("admin") // #2
				.password("password")
				.roles("ADMIN","USER");*/
	}

	private String basePath = "/api/rest/model/";
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/console", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests().antMatchers("/").permitAll().and()
				.authorizeRequests().antMatchers("/console/**").permitAll()
						.and().authorizeRequests().antMatchers(basePath+"").permitAll()
				.and().httpBasic()
				.and().formLogin().loginPage("/login").permitAll()
						.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

}
