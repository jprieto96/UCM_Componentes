package negocio.inventario.mostrarInventarioPorMaterial;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioMostrarInventarioPorMaterial {
	@BeforeClass
	public static void before(){
		//Partimos de que existe un departamento y un material, inventario
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		TDepartamento tDept2 = new TDepartamento(2, "Ventas", true);
		TDepartamento tDept3 = new TDepartamento(3, "Marketing", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
		saDepartamento.altaDepartamento(tDept2);
		saDepartamento.altaDepartamento(tDept3);
		
		TMaterial tMat = new TMaterial(1, "Ordenador", 550.36, true);
		TMaterial tMat2 = new TMaterial(2, "Pantallas", 299.95, true);


		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		saMaterial.altaMaterial(tMat);
		saMaterial.altaMaterial(tMat2);


		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		TInventario tInv = new TInventario(1, 1, 100, true);
		TInventario tInv2 = new TInventario(2, 1, 500, true);
		TInventario tInv3 = new TInventario(3, 1, 100, true);
		TInventario tInv4 = new TInventario(3, 2, 100, true);

		saInventario.altaInventario(tInv);
		saInventario.altaInventario(tInv2);
		saInventario.altaInventario(tInv3);
		saInventario.altaInventario(tInv4);

	}
	@Test
	public void mostrarInventarioPormaterial(){
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();

		//nos tiene que devolver los tres inv que hay del material con id =1
		List<TInventario> listInv = saInventario.mostrarInventarioPorMaterial(1);
		assertEquals(3, listInv.size());

		
	}
}
