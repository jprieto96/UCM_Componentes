package negocio.departamento.mostrarDepartamento;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioMostrarDepartamento {
	@BeforeClass
	public static void before(){
			//Partimos de que existe un departamento
			TDepartamento tDept = new TDepartamento(1, "I+D", true);
			SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
			saDepartamento.altaDepartamento(tDept);
			
	}
	@Test
	public void mostrarDepartamento(){
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		//mostramos el departamento id = 1
		TDepartamento tDept = saDepartamento.mostrarDepartamento(1);
		//aseguramos que los valores son los correctos
		assertEquals(1,tDept.getId());
		assertEquals("I+D",tDept.getNombre());
		assertEquals(true,tDept.getActivo());

	}
}
