package negocio.inventario.modificarInventario;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;
import negocio.inventario.Inventario;
import negocio.inventario.InventarioPK;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioModificarInventario {
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
		saInventario.altaInventario(tInv);
	}
	@Test
	public void bajaInventario(){
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();

		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		InventarioPK invPK = new InventarioPK(1, 1);
		Inventario inv = em.find(Inventario.class, invPK);		
		//Modificamos el inv
		inv.setExistencias(50);
		TInventario inventario = new TInventario(inv.getDepartamentoID(), inv.getMaterialID(), inv.getExistencias(), true);
		boolean ok = saInventario.modificarInventario(inventario);
		if(!ok){
			fail();
		}
		//intentamos modificar un inv que no existe
		TInventario inventario2 = new TInventario(1, 2, 100, true);
		boolean ok2 = saInventario.modificarInventario(inventario2);
		if(ok2){
			fail();
		}
		
	}
}
