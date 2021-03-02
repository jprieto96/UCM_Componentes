/**
 * 
 */
package negocio.material.imp;

import negocio.material.SAMaterial;
import negocio.material.TMaterial;
import negocio.inventario.Inventario;
import negocio.material.Material;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Query;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;

public class SAMaterialImp implements SAMaterial {

	public int altaMaterial(TMaterial material) {
		int id = -1;
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Material m = material.toEntity();

		try {
			Query q = em.createNamedQuery("negocio.material.Material.findBynombre");
			q.setParameter("nombre", material.getNombre());
			try {
				Material m2 = (Material) q.getSingleResult();
				if (m2 != null) {
					if (!m2.getActivo()) {
						m2.setActivo(true);
						em.getTransaction().commit();
						id = m2.getId();
					}
					else {
						em.getTransaction().rollback();
					}
				}
				else {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				// Esto ocurre en el caso de que sea nuevo
				if (m.getPrecio() > 0) { // evita que en la reactivacion cree un material erroneo
					m.setActivo(true);
					em.persist(m);
					em.getTransaction().commit();
					id = m.getId();
				}
				else {
					em.getTransaction().rollback();
				}
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			//emf.close();
		}
		return id;
	}

	public boolean bajaMaterial(int id) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		boolean exito = true;
		List<Inventario> lIn = new ArrayList<Inventario>();
		try {
			Material m = em.find(Material.class, id, LockModeType.OPTIMISTIC);
			if (m != null) { // si existe
				Query q = em.createNamedQuery("negocio.Inventario.findBymaterialID");
				try {
					lIn = q.setParameter("materialID", m.getId()).getResultList();
					if(estanActivosLosMaterialesEnInventarios(lIn)) {
						exito = false;
						em.getTransaction().rollback();
					}
					else {
						if (m.getActivo()) { // si esta activo
							m.setActivo(false);
							em.getTransaction().commit();
						} else { // si no esta activo (no se le puede dar de baja de nuevo)
							exito = false;
							em.getTransaction().rollback();
						}
					}
				}
				catch(Exception exe) {
					if (m.getActivo()) { // si esta activo
						m.setActivo(false);
						em.getTransaction().commit();
					} else { // si no esta activo (no se le puede dar de baja de nuevo)
						exito = false;
						em.getTransaction().rollback();
					}
				}
				
			} else { // no existe ese material
				exito = false;
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			exito = false;
		} finally {
			em.close();
		}
		return exito;
	}

	private boolean estanActivosLosMaterialesEnInventarios(List<Inventario> lIn) {
		for (Inventario inventario : lIn) {
			if(inventario.isActivo()) return true;
		}
		return false;
	}

	public boolean modificarMaterial(TMaterial material) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		boolean exito = true;
		try {
			Material m = em.find(Material.class, material.getId(), LockModeType.OPTIMISTIC);
			if (m != null) {
				Query q = em.createNamedQuery("negocio.material.Material.findBynombre");
				q.setParameter("nombre", material.getNombre());
				try {
					m = (Material) q.getSingleResult();
				} catch (Exception e) {
					// no se hace nada
				}
				m.setNombre(material.getNombre());
				m.setPrecio(material.getPrecio());
				em.getTransaction().commit();
			}
			else{
				em.getTransaction().rollback();
				exito = false;
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			exito = false;
		} finally {
			em.close();
		}

		return exito;
	}

	public TMaterial mostrarMaterial(int id) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Material m = null;
		TMaterial ma = null;
		try {
			m = em.find(Material.class, id, LockModeType.OPTIMISTIC);
			if (m != null) {
				ma = new TMaterial(m.getId(), m.getNombre(), m.getPrecio(), m.getActivo());
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return ma;
	}

	public List<TMaterial> listarMateriales(boolean activo) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<TMaterial> materiales = new ArrayList<TMaterial>();
		List<Material> m;

		if (activo) {
			m = em.createNamedQuery("negocio.material.Material.findAllActives").getResultList();
		} else {
			m = em.createNamedQuery("negocio.material.Material.findAll").getResultList();
		}
		for (Material material : m) {
			materiales.add(
					new TMaterial(material.getId(), material.getNombre(), material.getPrecio(), material.getActivo()));
		}
		em.getTransaction().commit();
		return materiales;
	}

}