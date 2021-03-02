package negocio.inventario.bajaInventario;

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
import negocio.inventario.Inventario;
import negocio.inventario.InventarioPK;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;
import negocio.material.Material;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioBajaInventario {
	@BeforeClass
	public static void before(){
		//Partimos de que existe un departamento y un material, inventario
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
		
		TMaterial tMat = new TMaterial(1, "Ordenador", 550.36, true);
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		saMaterial.altaMaterial(tMat);
		
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		TInventario tInv = new TInventario(1, 1, 100, true);
		boolean ok = saInventario.altaInventario(tInv);
		
	}
	@Test
	public void bajaInventario(){
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		//sabemos que existe un material y un departamrnto ambos con id = 1
		boolean ok = saInventario.bajaInventario(1, 1);
		

		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		InventarioPK invPK = new InventarioPK(1, 1);
		Inventario inv = em.find(Inventario.class, invPK);		
		assertEquals(false, inv.isActivo());
		
		

		
	}
}
