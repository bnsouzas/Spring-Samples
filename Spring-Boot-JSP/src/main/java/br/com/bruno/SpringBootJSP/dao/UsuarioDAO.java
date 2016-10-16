package br.com.bruno.SpringBootJSP.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import br.com.bruno.SpringBootJSP.model.Usuario;

@Repository
@Transactional
public class UsuarioDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Usuario> list(){
		TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u", Usuario.class);
		return query.getResultList();
	}
	
	private void criptografaSenha(Usuario usuario) {
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		usuario.setSenha(crypt.encode(usuario.getSenha()));
	}
	
	public Usuario get(String username) {
		return manager.find(Usuario.class, username);
	}
	
	public void add(Usuario usuario){
		criptografaSenha(usuario);
		manager.persist(usuario);
	}
	
	public void put(String username, Usuario usuario) {
		Usuario u = this.get(username);
		
		u.setEmail(usuario.getEmail());
		u.setNome(usuario.getNome());
		u.setUsername(usuario.getUsername());
		u.setRoles(usuario.getRoles());
		
		manager.merge(u);
	}

	public void delete(String username) {
		manager.remove(this.get(username));
	}
}
