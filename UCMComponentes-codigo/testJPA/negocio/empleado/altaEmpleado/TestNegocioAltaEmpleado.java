package negocio.empleado.altaEmpleado;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TRepartidor;
import negocio.empleado.TTienda;
import negocio.empleado.TipoEmpleado;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioAltaEmpleado {
	@BeforeClass
	public static void before(){
		//Partimos de que existe un departamento 
		TDepartamento tDept = new TDepartamento(1, "I+D", true);
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		saDepartamento.altaDepartamento(tDept);
	}
	@Test
	public void altaEmpleado(){
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		
		//Añadimos un empleado de tipo tienda
		TTienda tienda = new TTienda(1, "51704547Q", "Amparo", "Lopez arriba", 1500.65, TipoEmpleado.TiempoCompleto, true, "Ventanilla", 1);
		int id = saEmpleado.altaEmpleado(tienda);
		//Añadimos un empleado de tipo repartidor;
		TRepartidor repartidor = new TRepartidor(2, "04785456X", "Josete", "Jaka arriba", 1200.65, TipoEmpleado.TiempoCompleto, true, "Vallecas", 1);
		int id2 =saEmpleado.altaEmpleado(repartidor);

		// tiene que dar el id 1 e id 2, ya que son los dos primeros empleados
		assertEquals(1,id);
		assertEquals(2,id2);

		
		//Reactivacion de un empleado
		saEmpleado.bajaEmpleado(tienda.getId()); //Doy de baja el empleado de tipo tienda añadido anteriormente
		
		// tiene que devolver el mismo id, porque solo se ha reactivado
		int id3 = saEmpleado.altaEmpleado(tienda);
		assertEquals(id, id3);

		
		//Intento añadir un cliente, pero este ya existe y esta activo -> no deberia añadirlo, deberia dar error
		id = saEmpleado.altaEmpleado(repartidor);
		assertEquals(id, -1); //Pongo -1 porque da error y el sa me lo indica con un -1
		

		
	}

	
}
