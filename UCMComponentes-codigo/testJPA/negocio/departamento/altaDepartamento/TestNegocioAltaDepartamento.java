package negocio.departamento.altaDepartamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import integracion.entityManagerFactory.SingletonEntityManagerFactory;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.TCliente;
import negocio.cliente.TEmpresa;
import negocio.cliente.TParticular;
import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;
import util.BBDDManip;

public class TestNegocioAltaDepartamento {


	@Test
	public void altaDepartamento(){
		
		
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		int id = saDepartamento.altaDepartamento(tDept);
		
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Departamento dept = em.find(Departamento.class, id);
		//tiene que ser el mismo ID,NOMBRE Y ACTIVO
		assertEquals(dept.getId(), id);
		assertEquals(dept.getNombre(), tDept.getNombre());
		assertEquals(dept.getActivo(), tDept.getActivo());
		
		//Reactivado de departamento
		saDepartamento.bajaDepartamento(id);
		tDept = new TDepartamento(dept.getNombre());
		//Volvemos a dar de alta el departamento, tiene que tener el mismo id, ya que es una reactivacion
		int id2 = saDepartamento.altaDepartamento(tDept);
		em.getTransaction().commit();

		assertEquals(id,id2);

		em.close();
	}
	
}
