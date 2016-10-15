package br.com.bruno.SpringBootAngularJS.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.bruno.SpringBootAngularJS.model.Role;


@Repository
@Transactional
public class RoleDAO {
	@PersistenceContext
	private EntityManager manager;
	
	public List<Role> list(){
		TypedQuery<Role> query = manager.createQuery("select r from Role r", Role.class);
		return query.getResultList();
	}
}
