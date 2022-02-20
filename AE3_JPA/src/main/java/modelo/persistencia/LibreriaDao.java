package modelo.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.entidad.Libreria;

public class LibreriaDao {

	private EntityManager em;

	private boolean abrirConexion() {
		try {
			EntityManagerFactory factoria = Persistence.createEntityManagerFactory("LibreriaJPA");
			em = factoria.createEntityManager();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean cerrarConexion() {
		try {
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Libreria> listar() {
		if(!abrirConexion()) {
			return null;
		}		

		Query query = em.createQuery("select libreria from Libreria libreria");
		List<Libreria> librerias = query.getResultList();
		cerrarConexion();
		return librerias;
		}

}
