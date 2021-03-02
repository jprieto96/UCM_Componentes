/**
 * 
 */
package negocio.empleado.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;
import negocio.departamento.Departamento;
import negocio.empleado.Empleado;
import negocio.empleado.Repartidor;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.empleado.TRepartidor;
import negocio.empleado.TTienda;
import negocio.empleado.Tienda;
import negocio.empleado.TipoEmpleado;
import negocio.material.Material;

public class SAEmpleadoImp implements SAEmpleado {

	public int altaEmpleado(TEmpleado empleado) {
		int id = -1;
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Empleado e = empleado.toEntity();
		try {

			// Buscamos por ID ya que si al buscar no encontramos nada significa que estamos dando una alta nueva
			// en otro caso(caso de encontrar empleado por ID) significa que estamos reactivando el empleado
			Query q = em.createNamedQuery("negocio.empleado.Empleado.findByid");
			q.setParameter("id", e.getId());
			try {
				Empleado e2 = (Empleado) q.getSingleResult();
				if (e2 != null) {
					if (!e2.isActivo()) {
						e2.setActivo(true);
						em.getTransaction().commit();
						id = e2.getId();
					}
					else {
						em.getTransaction().rollback();
					}
				}
				else {
					em.getTransaction().rollback();
				}
			} catch (Exception ex) {
				//Buscamos el departamento asignado 
				Departamento d = em.find(Departamento.class, empleado.getIdDep(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				
				// Esto ocurre en el caso de que sea nuevo
				if (d.getActivo()) {
					e.setDepartamento(d);
					em.persist(e);
					em.getTransaction().commit();
					id = e.getId();
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
		return id;
	}

	public boolean bajaEmpleado(int id) {
		boolean ok = false;
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		try {
			Query q = em.createNamedQuery("negocio.empleado.Empleado.findByid");
			q.setParameter("id", id);
			try {
				Empleado e = (Empleado) q.getSingleResult();
				if (e != null) {
					if (e.isActivo()) {
						e.setActivo(false);
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
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return ok;
	}

	public TEmpleado mostrarEmpleado(int id) {
		TEmpleado tEmpleado = null;
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			Query q = em.createNamedQuery("negocio.empleado.Empleado.findByid");
			q.setParameter("id", id);
			try {
				Empleado e = (Empleado) q.getSingleResult();
				if (e != null) {
					if (e instanceof Tienda) {
						tEmpleado = new TTienda();
						tEmpleado = e.toTransfer();
					} else {
						tEmpleado = new TRepartidor();
						tEmpleado = e.toTransfer();
					}
					em.getTransaction().commit();
				} else {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

		return tEmpleado;
	}

	public boolean modificarEmpleado(TEmpleado empleado) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		boolean exito = false;
		try {
			Empleado e = em.find(Empleado.class, empleado.getId(), LockModeType.OPTIMISTIC);
			if (e != null) {
				//Buscamos el departamento asignado 
				Departamento d = em.find(Departamento.class, empleado.getIdDep(), LockModeType.OPTIMISTIC);
				if (d != null) {
					// Esto ocurre en el caso de que sea un departamento existente
					if (d.getActivo()) {
						e.setDepartamento(d);
						e.setNombre(empleado.getNombre());
						e.setApellidos(empleado.getApellidos());
						e.setDni(empleado.getDni());
						e.setSalario(empleado.getSalario());
						TipoEmpleado t = null;
						if (empleado.getTipo().equals("Tiempo parcial")) {
							t = TipoEmpleado.TiempoParcial;
						} else if (empleado.getTipo().equals("Tiempo completo")) {
							t = TipoEmpleado.TiempoCompleto;
						}
						e.setTipo(t);
						if (e instanceof Tienda) {
							((Tienda) e).setPuesto(((TTienda) empleado).getPuesto());
						} else {
							((Repartidor) e).setZonaReparto(((TRepartidor) empleado).getZonaReparto());
						}
						exito = true;
						em.getTransaction().commit();
					}
					else {
						em.getTransaction().rollback();
					}
				}
				else {
					em.getTransaction().rollback();
				}
			}
			else {
				em.getTransaction().rollback();
			}
		} catch (RollbackException ex) {
			ex.printStackTrace();
			exito = false;
		} catch (Exception e) {
			em.getTransaction().rollback();
			exito = false;
		} finally {
			em.close();
		}

		return exito;
	}

	public List<TEmpleado> listarEmpleados(boolean activo) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		List<TEmpleado> empleados = new ArrayList<TEmpleado>();
		List<Empleado> e;
		if (activo) {
			e = em.createNamedQuery("negocio.empleado.Empleado.findAllActives").getResultList();
		} else {
			e = em.createNamedQuery("negocio.empleado.Empleado.findAll").getResultList();
		}
		for (Empleado empleado : e) {
			empleados.add(empleado.toTransfer());
		}
		return empleados;
	}

	public List<TEmpleado> mostrarEmpleadosPorDepartamento(int id) {
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		List<TEmpleado> empleados = new ArrayList<TEmpleado>();
		List<Empleado> e;
		Query q = em.createNamedQuery("negocio.empleado.Empleado.findBydepartamento");
		q.setParameter("departamento", id);
		e = q.getResultList();
		for (Empleado empleado : e) {
			empleados.add(empleado.toTransfer());
		}
		return empleados;
	}

	
}