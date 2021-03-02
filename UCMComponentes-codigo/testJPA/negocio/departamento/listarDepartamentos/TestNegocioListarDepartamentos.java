package negocio.departamento.listarDepartamentos;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;
import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioListarDepartamentos {
	@BeforeClass
	public static void before(){
		//Partimos de que existen 3 departamentos 
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		TDepartamento tDept2 = new TDepartamento(2, "Ventas", true);
		TDepartamento tDept3 = new TDepartamento(3, "Marketing", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
		saDepartamento.altaDepartamento(tDept2);
		saDepartamento.altaDepartamento(tDept3);

	}
	@Test
	public void listarDepartamentos(){
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		//Damos de baja al departamento id = 3
		boolean ok = saDepartamento.bajaDepartamento(3);
		//listamos todos los departamentos
		List<TDepartamento> listDept = saDepartamento.listarDepartamentos(false);
		//Me deberia devolver 3
		assertEquals(3,listDept.size());
		//Listamos solo los activos
		List<TDepartamento> listDeptActivos = saDepartamento.listarDepartamentos(true);
		//Me deberia devolver 2
		assertEquals(2, listDeptActivos.size());
		
	}
}
