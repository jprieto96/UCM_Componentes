package negocio.inventario.listarInventarios;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.factoria.FactoriaNegocio;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioListarInventarios {
	@BeforeClass
	public static void before(){
		//Partimos de que existe un departamento y un material, inventario
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		TDepartamento tDept2 = new TDepartamento(2, "Ventas", true);

		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
		saDepartamento.altaDepartamento(tDept2);

		TMaterial tMat = new TMaterial(1, "Ordenador", 550.36, true);
		TMaterial tMat2 = new TMaterial(2, "Altavoz", 75.95, true);

		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		saMaterial.altaMaterial(tMat);
		saMaterial.altaMaterial(tMat2);

		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		TInventario tInv = new TInventario(1, 1, 100, true);
		TInventario tInv2 = new TInventario(1, 2, 200, true);
		TInventario tInv3 = new TInventario(2, 2, 500, true);

		saInventario.altaInventario(tInv);
		saInventario.altaInventario(tInv2);
		saInventario.altaInventario(tInv3);
		
	}
	@Test
	public void listarInventarios(){
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		//Damos de baja al inventario id = 3
		boolean ok = saInventario.bajaInventario(1, 1);
		//listamos todos los inventario
		List<TInventario> listInv = saInventario.listarInventarios(false);
		//Me deberia devolver 3
		assertEquals(3,listInv.size());
		//Listamos solo los activos
		List<TInventario> listInvActivos = saInventario.listarInventarios(true);
		//Me deberia devolver 2
		assertEquals(2, listInvActivos.size());
		
	}
}
