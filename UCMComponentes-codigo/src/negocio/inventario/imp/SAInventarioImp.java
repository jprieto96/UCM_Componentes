/**
 * 
 */
package negocio.inventario.imp;

import negocio.departamento.Departamento;

import negocio.inventario.Inventario;
import negocio.inventario.InventarioPK;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;
import negocio.material.Material;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;

public class SAInventarioImp implements SAInventario {

	public boolean altaInventario(TInventario inventario) {
		boolean ok = false;
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Inventario i = inventario.toEntity();
		try {
			InventarioPK ipk = new InventarioPK(i.getDepartamentoID(), i.getMaterialID());
			Inventario i2 = em.find(Inventario.class, ipk, LockModeType.OPTIMISTIC);
			if (i2 != null) {
				if (!i2.isActivo()) {
					i2.setActivo(true);
					i2.setExistencias(inventario.getExistencias());
					em.getTransaction().commit();
					ok = true;
				}
				else{
					ok =false;
					em.getTransaction().rollback();
				}
			}
			else {
				//Buscamos el departamento asignado 
				Departamento d = em.find(Departamento.class, i.getDepartamentoID(), LockModeType.OPTIMISTIC);
				
				//Buscamos el material asignado 
				Material m = em.find(Material.class, i.getMaterialID(), LockModeType.OPTIMISTIC);
				
				// Esto ocurre en el caso de que sea nuevo
				if (d.getActivo() && m.getActivo()) {
					i.setDepartamento(d);
					i.setMaterial(m);
					em.persist(i);
					em.getTransaction().commit();
					ok = true;
				}
				else {
					em.getTransaction().rollback();
				}
			}
		} catch (RollbackException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return ok;
	}

	public boolean modificarInventario(TInventario inventario) {
		boolean exito = true;
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Inventario inv =null;
		Inventario i = inventario.toEntity();
		try {
			InventarioPK ipk = new InventarioPK(i.getDepartamentoID(), i.getMaterialID());
			inv = em.find(Inventario.class, ipk, LockModeType.OPTIMISTIC);
			if(inv != null){
				//Comprobamos los nuevos datos
				int nuevoDepartamento = inventario.getIdDepartamento();
				int nuevoMaterial = inventario.getIdMaterial();
				Departamento d = em.find(Departamento.class, nuevoDepartamento, LockModeType.OPTIMISTIC);
				Material m = em.find(Material.class, nuevoMaterial, LockModeType.OPTIMISTIC);
				if(d != null && m!= null){
					inv.setDepartamentoID(inventario.getIdDepartamento());
					inv.setMaterialID(inventario.getIdMaterial());
					int nuevasExistencias =inventario.getExistencias();
					if(nuevasExistencias >= 0)
						inv.setExistencias(nuevasExistencias);
					em.getTransaction().commit();
				}
				else{
					exito =false;
					em.getTransaction().rollback();
				}
				
			}
			else{
				exito =false;
				em.getTransaction().rollback();
			}
			} catch (Exception e) {
				em.getTransaction().rollback();
				exito = false;
			}finally {
				em.close();
			}
		
		
		return exito;
	}
	
	public boolean bajaInventario(int idMaterial, int idDepartamento) {
		boolean ok = false;
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			InventarioPK ipk = new InventarioPK(idDepartamento, idMaterial);
			Inventario i = em.find(Inventario.class, ipk, LockModeType.OPTIMISTIC);
			if (i != null) {
				if (i.isActivo()) {
					i.setActivo(false);
					em.getTransaction().commit();
					ok = true;
				} else {
					em.getTransaction().rollback();
				}
			}
			else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return ok;
	}

	public TInventario mostrarInventario(int idMaterial, int idDepartamento) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Inventario inv = null;
		TInventario inventario =null;
		try {
			InventarioPK ipk = new InventarioPK(idDepartamento, idMaterial);
			inv = em.find(Inventario.class, ipk, LockModeType.OPTIMISTIC);
			if(inv != null){
				inventario =new TInventario(inv.getDepartamentoID(), inv.getMaterialID(), inv.getExistencias(), inv.isActivo());
				em.getTransaction().commit();
			}
			else{
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return inventario;
	}

	public List<TInventario> listarInventarios(boolean activo) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<TInventario> inventarios = new ArrayList<TInventario>();
		List<Inventario> inv;
		if(activo){
			inv = em.createNamedQuery("negocio.Inventario.findAllActives").getResultList();
		}else{
			inv = em.createNamedQuery("negocio.Inventario.findAll").getResultList();
		}
		for(Inventario inventario: inv){
			inventarios.add(
					new TInventario(inventario.getDepartamentoID(), inventario.getMaterialID(), inventario.getExistencias(), inventario.isActivo()));
		}
		em.getTransaction().commit();
		return inventarios;
	}

	public List<TInventario> mostrarInventarioPorMaterial(int id) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<TInventario> inventarios = new ArrayList<TInventario>();
		List<Inventario> inv;
		Query q = em.createNamedQuery("negocio.Inventario.findBymaterialID");
		q.setParameter("materialID", id);
		inv = q.getResultList();
		for (Inventario inventario : inv) {
			inventarios.add( 
					new TInventario(inventario.getDepartamentoID(), inventario.getMaterialID(), inventario.getExistencias(), inventario.isActivo()));
		}
		em.getTransaction().commit();
		return inventarios;
	}

	public List<TInventario> mostrarInventarioPorDepartamento(int id) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<TInventario> inventarios = new ArrayList<TInventario>();
		List<Inventario> inv;
		Query q = em.createNamedQuery("negocio.Inventario.findBydepartamentoID");
		q.setParameter("departamentoID", id);
		inv = q.getResultList();
		for (Inventario inventario : inv) {
			inventarios.add(
					new TInventario(inventario.getDepartamentoID(), inventario.getMaterialID(), inventario.getExistencias(), inventario.isActivo()));
		}
		em.getTransaction().commit();
		return inventarios;
	}

}