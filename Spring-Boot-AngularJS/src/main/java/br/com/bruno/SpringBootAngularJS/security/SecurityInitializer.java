package br.com.bruno.SpringBootAngularJS.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import br.com.bruno.SpringBootAngularJS.HttpSessionConfig;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	public SecurityInitializer() {
		super(SecurityConfiguration.class, HttpSessionConfig.class);
	}
}
