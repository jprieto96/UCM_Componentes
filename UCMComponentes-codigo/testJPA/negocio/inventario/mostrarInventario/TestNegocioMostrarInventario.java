package negocio.inventario.mostrarInventario;

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

public class TestNegocioMostrarInventario {
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
	public void mostrarInventario(){
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();

		TInventario tInv = saInventario.mostrarInventario(1, 1);
		assertEquals(1,tInv.getIdDepartamento());
		assertEquals(1,tInv.getIdMaterial());
		assertEquals(100,tInv.getExistencias());

		
	}
}
