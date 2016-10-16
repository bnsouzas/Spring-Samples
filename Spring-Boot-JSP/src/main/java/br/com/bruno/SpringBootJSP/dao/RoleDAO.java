package br.com.bruno.SpringBootJSP.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.bruno.SpringBootJSP.model.Role;


@Repository
@Transactional
public class RoleDAO {
	@PersistenceContext
	private EntityManager manager;
	
	public List<Role> list(){
		TypedQuery<Role> query = manager.createQuery("select r from Role r", Role.class);
		return query.getResultList();
	}
	
	public Role get(String role) {
		return manager.find(Role.class, role);
	}

	public void add(Role role) {
		manager.persist(role);
	}
}
