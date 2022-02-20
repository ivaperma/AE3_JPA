package modelo.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.entidad.Autor;

public class AutorDao {
	
	private EntityManager em;
	
	private boolean abrirConexion() {
		try {
			EntityManagerFactory factoria = Persistence.createEntityManagerFactory("AutorJPA");
			em = factoria.createEntityManager();
			return true;
			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean cerrarConexion(){
		try {
			em.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public int añadir(Autor autor) {
		if(!abrirConexion()) {
			return 0;
		}
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(autor);
		et.commit();
		cerrarConexion();
		return autor.getId();
	}
	
	public int borrar(int id) {
		if(!abrirConexion()) {
			return 0;
		}

		Autor pAux = buscar(id);
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(pAux);
		et.commit();
		cerrarConexion();
		return pAux.getId();
	}
	
	public Autor buscar(int id) {
		if(!abrirConexion()) {
			return null;
		}
		return em.find(Autor.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Autor> listar() {
		if(!abrirConexion()) {
			return null;
		}		
		
		Query query = em.createQuery("select autor from Autor autor");
		List<Autor> listaAutores = query.getResultList();
		cerrarConexion();
		return listaAutores;
	}

}
