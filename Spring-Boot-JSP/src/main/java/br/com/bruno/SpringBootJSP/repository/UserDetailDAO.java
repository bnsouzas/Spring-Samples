package br.com.bruno.SpringBootJSP.repository;

import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.LocaleResolver;

import br.com.bruno.SpringBootJSP.model.Usuario;

@Repository
public class UserDetailDAO implements UserDetailsService {
	@PersistenceContext
	private EntityManager manager;
	
	private MessageSource messageSource;
	private LocaleResolver localeResolver;
	private HttpServletRequest request;
	
	@Autowired
	public UserDetailDAO(MessageSource messageSource, LocaleResolver localeResolver,
			HttpServletRequest request) {
		this.messageSource = messageSource;
		this.localeResolver = localeResolver;
		this.request = request;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.username = :username", Usuario.class)
                .setParameter("username", username)
                .getResultList();
		
        if(usuarios.isEmpty()){
        	Locale locale = localeResolver.resolveLocale(request);
        	System.out.println(locale);
        	System.out.println(messageSource.getMessage("login.user.notFound", null, locale));
            throw new UsernameNotFoundException(messageSource.getMessage("login.user.notFound", null, locale));
        }
        
        return usuarios.get(0);
	}
}
