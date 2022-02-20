package modelo.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.entidad.Libro;

public class LibroDao {
	
	private EntityManager em;
	
	private boolean abrirConexion() {
		try {
			EntityManagerFactory factoria = Persistence.createEntityManagerFactory("LibreriaJPA");
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
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Libro> listar() {
		if(!abrirConexion()) {
			return null;
		}		
		
		Query query = em.createQuery("select libro from Libros libro");
		List<Libro> libros = query.getResultList();
		cerrarConexion();
		return libros;
	}

}
