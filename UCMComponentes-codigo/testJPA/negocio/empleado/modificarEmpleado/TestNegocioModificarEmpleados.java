package negocio.empleado.modificarEmpleado;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;
import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.Empleado;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.empleado.TRepartidor;
import negocio.empleado.TTienda;
import negocio.empleado.TipoEmpleado;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioModificarEmpleados {
	@BeforeClass
	public static void before(){
		//Partimos de que existe un departamento y dos empleados de caada tipo
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
		
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		//Añadimos un empleado de tipo tienda
		TTienda tienda = new TTienda(1, "51704547Q", "Amparo", "Lopez arriba", 1500.65, TipoEmpleado.TiempoCompleto, true, "Ventanilla", 1);
		int id = saEmpleado.altaEmpleado(tienda);
		//Añadimos un empleado de tipo repartidor;
		TRepartidor repartidor = new TRepartidor(2, "04785456X", "Josete", "Jaka arriba", 1200.65, TipoEmpleado.TiempoCompleto, true, "Vallecas", 1);
		int id2 =saEmpleado.altaEmpleado(repartidor);
	}
	@Test
	public void modificarEmpleado(){
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		//intentamos modificar un empleado que no existe
		TTienda tienda2 = new TTienda(2, "25687452L", "osito", "baby boom", 500.65, TipoEmpleado.TiempoParcial, true, "Ventanilla", 1);
		boolean ok = saEmpleado.modificarEmpleado(tienda2);
		if(ok){ // deberia dar false, si no modifica un empleado que no existe
			fail();
		}
		
		TEmpleado tEmp = saEmpleado.mostrarEmpleado(1);
		//lo modificamos
		tEmp.setApellidos("Lopez abajo");
		tEmp.setSalario(1600.95);
		boolean ok2 = saEmpleado.modificarEmpleado(tEmp);
		if(!ok2){ // tiene que estar a true
			fail();
		}
		
		//intentamos modificar un departamento inexistente
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		TEmpleado tEmp2 = saEmpleado.mostrarEmpleado(2);
		//lo modificamos
		//intentamos modificar al departamento 2 que lo recibe de la capa de presentacion
		Departamento dept = em.find(Departamento.class, 2);
		//como no existe el dept no se puede modificar
		assertEquals(null, dept);
				
		em.getTransaction().commit();
		em.close();
	}
}
