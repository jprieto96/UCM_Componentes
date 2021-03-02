package negocio.departamento.calculoNomina;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TRepartidor;
import negocio.empleado.TTienda;
import negocio.empleado.TipoEmpleado;
import negocio.factoria.FactoriaNegocio;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioCalculoNomina {
	@BeforeClass
	public static void before(){
	//Partimos de que existe un departamento y empleados
		
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
	
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		//Añadimos un empleado de tipo tienda
		TTienda tienda = new TTienda(1, "51704547Q", "Amparo", "Lopez arriba", 1500.65, TipoEmpleado.TiempoCompleto, true, "Ventanilla", 1);
		int id = saEmpleado.altaEmpleado(tienda);
		//Añadimos un empleado de tipo repartidor;
		TRepartidor repartidor = new TRepartidor(2, "04785456X", "Josete", "Jaka arriba", 1200.65, TipoEmpleado.TiempoCompleto, true, "Vallecas", 1);
		int id2 =saEmpleado.altaEmpleado(repartidor);
	}
	@Test
	public void calculoNomina(){
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		List<String> infoNomina = saDepartamento.calculoNominaDepartamento(1);
		DecimalFormat df = new DecimalFormat("#.00");
		String nomina = infoNomina.get(0);
		double n = Double.parseDouble(nomina);
		String valor = df.format(n);
		//Sabemos el valor que tiene que dar porque lo metemos los datos a mano
		assertEquals("2708,74",valor );
		

		
	}
}
