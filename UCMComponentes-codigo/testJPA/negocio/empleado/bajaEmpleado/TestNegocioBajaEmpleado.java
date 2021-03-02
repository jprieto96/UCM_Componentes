package negocio.empleado.bajaEmpleado;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.Empleado;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TRepartidor;
import negocio.empleado.TTienda;
import negocio.empleado.TipoEmpleado;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioBajaEmpleado {
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
	public void bajaEmpleado(){
		
		//Damos de baja un empleado con id = 1
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		boolean ok = saEmpleado.bajaEmpleado(1);
		
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Recuperamos el empleado
		Empleado emp = em.find(Empleado.class, 1);
		assertEquals(false, emp.isActivo());
		assertEquals(true, ok);
	}
}
