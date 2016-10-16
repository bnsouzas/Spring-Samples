package br.com.bruno.SpringBootJSP.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.bruno.SpringBootJSP.model.Artigo;
import br.com.bruno.SpringBootJSP.model.Usuario;

@Repository
@Transactional
public class ArtigoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public Artigo getArtigo(Usuario usuario, int id) {
		Artigo artigo = manager.createQuery("select a from Artigo a where a.id = :id and a.usuario = :usuario", Artigo.class)
							   .setParameter("id", id)
			                   .setParameter("usuario", usuario)
			                   .getSingleResult();
		return artigo;
	}
	
	public void add(Artigo artigo) {
		manager.persist(artigo);
	}

	public void put(Artigo artigo) {
		manager.merge(artigo);
	}

	public List<Artigo> list(Usuario usuario) {
		TypedQuery<Artigo> query = manager.createQuery("select a from Artigo a where a.usuario = :usuario", Artigo.class)
										  .setParameter("usuario", usuario);
		return query.getResultList();
	}

	public void delete(int idParam, Usuario usuario) {
		manager.remove(getArtigo(usuario, idParam));
	}

}
