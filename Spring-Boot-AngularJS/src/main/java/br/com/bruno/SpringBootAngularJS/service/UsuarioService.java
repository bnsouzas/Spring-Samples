package br.com.bruno.SpringBootAngularJS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.bruno.SpringBootAngularJS.dao.UsuarioDAO;
import br.com.bruno.SpringBootAngularJS.model.Role;
import br.com.bruno.SpringBootAngularJS.model.Usuario;

@Service
public class UsuarioService implements UserDetailsService {
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public Usuario logado(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal != "anonymousUser")
			return (Usuario)principal;
		
		return new Usuario();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioDAO.get(username);
	}
	
	public List<Usuario> list(){
		return usuarioDAO.list();
	}
	
	public void put(String username, Usuario usuario) {
		usuarioDAO.put(username, usuario);
	}
	
	public void add(Usuario usuario) {
		usuario.getRoles().add(new Role("ROLE_USER"));
		usuarioDAO.add(usuario);
	}
	
	public void delete(String username) {
		usuarioDAO.delete(username);
	}
}
