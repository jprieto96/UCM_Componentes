package negocio.empleado.mostrarEmpleado;

import static org.junit.Assert.assertEquals;

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

public class TestNegocioMostrarEmpleado {
	@BeforeClass
	public static void before(){
		//Partimos de que existe un Empleado y dos empleados de caada tipo
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
	public void mostrarEmpleado(){
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		//mostramos el Empleado id = 1
		TEmpleado tEmp = saEmpleado.mostrarEmpleado(1);
		String sueldo = String.valueOf(tEmp.getSalario());
		String tipo = tEmp.getTipo();
		//aseguramos que los valores son los correctos
		assertEquals("Amparo", tEmp.getNombre());
		assertEquals("Lopez arriba", tEmp.getApellidos());
		assertEquals("Tiempo completo", tipo);
		assertEquals("1500.65", sueldo);
		assertEquals("51704547Q", tEmp.getDni());
		assertEquals(true, tEmp.isActivo());

	}
}
