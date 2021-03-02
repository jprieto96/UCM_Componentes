package negocio.inventario.mostrarInventarioPorDepartamento;

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

public class TestNegocioMostrarInventarioPorDepartamento {
	@BeforeClass
	public static void before(){
		//Partimos de que existe un departamento y un material, inventario
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		TDepartamento tDept2 = new TDepartamento(1, "Ventas", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
		saDepartamento.altaDepartamento(tDept2);

		TMaterial tMat = new TMaterial(1, "Ordenador", 550.36, true);
		TMaterial tMat2 = new TMaterial(2, "Pantalla", 299.95, true);
		TMaterial tMat3 = new TMaterial(3, "Raton", 12.36, true);

		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		saMaterial.altaMaterial(tMat);
		saMaterial.altaMaterial(tMat2);
		saMaterial.altaMaterial(tMat3);

		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		TInventario tInv = new TInventario(1, 1, 100, true);
		TInventario tInv2 = new TInventario(1, 2, 500, true);
		TInventario tInv3 = new TInventario(1, 3, 100, true);
		TInventario tInv4 = new TInventario(2, 1, 100, true);

		saInventario.altaInventario(tInv);
		saInventario.altaInventario(tInv2);
		saInventario.altaInventario(tInv3);
		saInventario.altaInventario(tInv4);

	}
	@Test
	public void mostrarInventarioPorDepartamento(){
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();

		//nos tiene que devolver los tres inv que hay del departamento con id =1
		List<TInventario> listInv = saInventario.mostrarInventarioPorDepartamento(1);
		assertEquals(3, listInv.size());

		
	}
}
