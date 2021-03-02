package negocio.departamento.bajaDepartamento;

import static org.junit.Assert.assertEquals;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import integracion.entityManagerFactory.SingletonEntityManagerFactory;
import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioBajaDepartamento {

	@BeforeClass
	public static void before(){
		//Partimos de que existe un departamento 
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
	}
	@Test
	public void bajaDepartamento(){
		//damos de baja ese departamento que sabemos que tiene id = 1
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		boolean ok = saDepartamento.bajaDepartamento(1);

		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Recuperamos el departamento para ver si esta dado de baja correctamente
		Departamento dept = em.find(Departamento.class, 1);
		assertEquals(false,dept.getActivo());
		assertEquals(true, ok);
		
	}
}
	