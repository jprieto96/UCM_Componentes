package negocio.inventario.altaInventario;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;
import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TRepartidor;
import negocio.empleado.TTienda;
import negocio.empleado.TipoEmpleado;
import negocio.factoria.FactoriaNegocio;
import negocio.inventario.Inventario;
import negocio.inventario.InventarioPK;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;
import negocio.material.Material;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioAltaInventario {
	@BeforeClass
	public static void before(){
		//Partimos de que existe un departamento y un material 
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
		
		TMaterial tMat = new TMaterial(1, "Ordenador", 550.36, true);
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		saMaterial.altaMaterial(tMat);
		
		
		
	}
	@Test
	public void altaInventario(){
		//Partimos de que conocemos ya el departameno y material por eso creamos el transfer directamente
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		TInventario tInv = new TInventario(1, 1, 100, true);
		boolean ok = saInventario.altaInventario(tInv);
		
		assertEquals(true, ok);
		
		//Reactivacion
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		saInventario.bajaInventario(1, 1);
		//volvemos a dar de alta
		InventarioPK invPK = new InventarioPK(1, 1);
		Inventario inv = em.find(Inventario.class, invPK);
		TInventario tInv2 = new TInventario(inv.getDepartamentoID(), inv.getMaterialID(), inv.getExistencias(), inv.isActivo());
		boolean ok2 = saInventario.altaInventario(tInv2);
		assertEquals(true, ok2);
		
		//intentamos añadir un inventario con dept inexistente
		//intentamos agregar el departamento con id = 2 que no existe
		//el material si existe
		Departamento dep = em.find(Departamento.class, 2);
		Material mat = em.find(Material.class, 1);
		assertEquals(null, dep);
		assertEquals(1, mat.getId());
		
		//intentamos añadir un inventario con material inexistente
		TInventario tInv3 = new TInventario(1, 2, 100, true);
		Departamento dep2 = em.find(Departamento.class, 1);
		Material mat2 = em.find(Material.class, 2);
		assertEquals(1, dep2.getId());
		assertEquals(null, mat2);
		

		
	}
}
