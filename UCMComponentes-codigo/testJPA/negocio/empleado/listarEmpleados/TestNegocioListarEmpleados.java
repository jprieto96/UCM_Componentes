package negocio.empleado.listarEmpleados;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.empleado.TRepartidor;
import negocio.empleado.TTienda;
import negocio.empleado.TipoEmpleado;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioListarEmpleados {
	
	@BeforeClass
	public static void before(){
		//Partimos de que existe un departamento y tres empleados de caada tipo
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
		
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		//Añadimos dos empleados de tipo tienda
		TTienda tienda = new TTienda(1, "51704547Q", "Amparo", "Lopez arriba", 1500.65, TipoEmpleado.TiempoCompleto, true, "Ventanilla", 1);
		TTienda tienda2 = new TTienda(2, "25687452L", "osito", "baby boom", 500.65, TipoEmpleado.TiempoParcial, true, "Ventanilla", 1);
		int id = saEmpleado.altaEmpleado(tienda);
		int id2 = saEmpleado.altaEmpleado(tienda2);

		//Añadimos un empleado de tipo repartidor;
		TRepartidor repartidor = new TRepartidor(3, "04785456X", "Josete", "Jaka arriba", 1200.65, TipoEmpleado.TiempoCompleto, true, "Vallecas", 1);
		int id3 =saEmpleado.altaEmpleado(repartidor);
	}
	
	@Test
	public void listarEmpleados(){
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		//Damos de baja al empleado id = 2
		boolean ok = saEmpleado.bajaEmpleado(2);
		//listamos todos los empleados
		List<TEmpleado> listEmp = saEmpleado.listarEmpleados(false);
		//Me deberia devolver 3
		assertEquals(3,listEmp.size());
		//Listamos solo los activos
		List<TEmpleado> listDeptActivos = saEmpleado.listarEmpleados(true);
		//Me deberia devolver 2
		assertEquals(2, listDeptActivos.size());
		
	}
}
