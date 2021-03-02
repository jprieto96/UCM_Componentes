package negocio.departamento.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Query;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;
import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.Empleado;
import negocio.inventario.Inventario;
import negocio.inventario.InventarioPK;
import negocio.material.Material;
import negocio.material.TMaterial;

public class SADepartamentoImp implements SADepartamento {

	@Override
	public int altaDepartamento(TDepartamento departamento) {
		int id = -1;
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Departamento d = departamento.toEntity();
		try {
			Query q = em.createNamedQuery("negocio.departamento.Departamento.findBynombre");
			q.setParameter("nombre", departamento.getNombre());
			try {
				d = (Departamento) q.getSingleResult();
				if (d != null) {
					if (!d.getActivo()) {
						d.setActivo(true);
						em.getTransaction().commit();
						id = d.getId();
					}
					else {
						em.getTransaction().rollback();
					}
				}
				else {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				d.setActivo(true);
				em.persist(d);
				em.getTransaction().commit();
				id = d.getId();
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return id;
	}

	@Override
	public boolean bajaDepartamento(int id) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		boolean exito = true;
		List<Inventario> lIn = new ArrayList<Inventario>();
		try {
			Departamento d = em.find(Departamento.class, id, LockModeType.OPTIMISTIC);
			if (d != null) { // si existe
				Query q = em.createNamedQuery("negocio.Inventario.findBydepartamentoID");
				try {
					lIn = q.setParameter("departamentoID", d.getId()).getResultList();
					// Comprobar que no se pueda dar de baja un departamento que esta 
					if(estanActivosLosDepartamentosEnInventarios(lIn)) {
						exito = false;
						em.getTransaction().rollback();
					}
					else {
						if (d.getActivo()) { // si esta activo
							d.setActivo(false);
							
							em.getTransaction().commit();
						} else { // si no esta activo (no se le puede dar de baja de nuevo)
							em.getTransaction().rollback();
							exito = false;
						}
					}	
				}
				catch(Exception exe) {
					if (d.getActivo()) { // si esta activo
						d.setActivo(false);
						em.getTransaction().commit();
					} else { // si no esta activo (no se le puede dar de baja de nuevo)
						em.getTransaction().rollback();
						exito = false;
					}
				}
				
			} else { // no existe ese material
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

	private boolean estanActivosLosDepartamentosEnInventarios(List<Inventario> lIn) {
		for (Inventario inventario : lIn) {
			if(inventario.isActivo()) return true;
		}
		return false;
	}

	@Override
	public boolean modificarDepartamento(TDepartamento departamento) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		boolean exito = true;
		try {
			Departamento d = em.find(Departamento.class, departamento.getId(), LockModeType.OPTIMISTIC);
			if (d != null) {
				Query q = em.createNamedQuery("negocio.departamento.Departamento.findBynombre");
				q.setParameter("nombre", departamento.getNombre());
				try {
					d = (Departamento) q.getSingleResult();
				} catch (Exception e) {
					// no se hace nada
				}
				d.setNombre(departamento.getNombre());
				em.getTransaction().commit();
			}
			else {
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

	@Override
	public TDepartamento mostrarDepartamento(int id) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Departamento d = null;
		TDepartamento tD = null;
		try {
			d = em.find(Departamento.class, id, LockModeType.OPTIMISTIC);
			if (d != null) {
				tD = new TDepartamento(d.getId(), d.getNombre(), d.getActivo());
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

		return tD;
	}

	@Override
	public List<TDepartamento> listarDepartamentos(boolean activo) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Departamento> departamentos;
		List<TDepartamento> d = new ArrayList<TDepartamento>();
		if (activo) {
			departamentos = em.createNamedQuery("negocio.departamento.Departamento.findAllActives").getResultList();
		} else {
			departamentos = em.createNamedQuery("negocio.departamento.Departamento.findAll").getResultList();
		}
		for (Departamento dep : departamentos) {
			d.add(new TDepartamento(dep.getId(), dep.getNombre(), dep.getActivo()));
		}
		em.getTransaction().commit();
		return d;
	}

	@Override
	public List<String> calculoNominaDepartamento(int id) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<String> infoNomina =  new ArrayList<String>(2);
		
		//Solo hace falta de manera optimista 
		Departamento dep = em.find(Departamento.class, id, LockModeType.OPTIMISTIC);
		//Buscamos los empleados
		Query q = em.createNamedQuery("negocio.empleado.Empleado.findBydepartamento");
		q.setParameter("departamento", id);
		List<Empleado> empleados = q.getResultList();
		Empleado emp;
		double nomina = 0;
		infoNomina.add(Double.toString(nomina));
		//Calculamos la nomina del departamento
		if(empleados.isEmpty()) infoNomina.set(0, "0.0");
		else{
			for(int i = 0;i<empleados.size(); i++){
				emp = empleados.get(i);
				em.lock(emp, LockModeType.OPTIMISTIC); //Bloqueamos de forma optimista
				nomina += emp.calcularNomina();
			}
			infoNomina.add(Double.toString(empleados.size()));
			infoNomina.set(0, Double.toString(nomina));
		}
		
		em.getTransaction().commit();

		return infoNomina;
	}
}
