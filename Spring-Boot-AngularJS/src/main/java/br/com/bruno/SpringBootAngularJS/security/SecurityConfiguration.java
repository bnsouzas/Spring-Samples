package br.com.bruno.SpringBootAngularJS.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.bruno.SpringBootAngularJS.service.UsuarioService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final RESTAuthenticationEntryPoint authenticationEntryPoint;
	private final RESTAuthenticationFailureHandler authenticationFailureHandler;
	private final RESTAuthenticationSuccessHandler authenticationSuccessHandler;
	private final UsuarioService usuarioService;

	@Autowired
	public SecurityConfiguration(RESTAuthenticationEntryPoint authenticationEntryPoint,
			RESTAuthenticationFailureHandler authenticationFailureHandler,
			RESTAuthenticationSuccessHandler authenticationSuccessHandler, UsuarioService usuarioService) {
		super();
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFailureHandler = authenticationFailureHandler;
		this.authenticationSuccessHandler = authenticationSuccessHandler;
		this.usuarioService = usuarioService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST,"/api/usuario/").permitAll()
				.antMatchers("/api/usuario/**").hasRole("ADMIN")
				.antMatchers("/api/**").permitAll()
				.anyRequest().permitAll()
			.and()
				.csrf().disable()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
			.and()
				.formLogin()
					.successHandler(authenticationSuccessHandler)
					.failureHandler(authenticationFailureHandler)
			.and()
				.logout()
					.logoutSuccessHandler(new RESTLogoutSuccessHandler());
				
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(usuarioService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
}
