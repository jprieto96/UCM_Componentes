package negocio.departamento.modificarDepartamento;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioModicarDepartamento {
		
	@BeforeClass
	public static void before(){
			//Partimos de 
			TDepartamento tDept = new TDepartamento(1, "I+D", true);
			SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
			saDepartamento.altaDepartamento(tDept);
			
	}
	@Test
	public void modificarDepartamento(){
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		//Intentamos modificar un departamento que no existe
		TDepartamento tDept = new TDepartamento(2, "Ventas", true);
		boolean ok = saDepartamento.modificarDepartamento(tDept);
		if(ok){ // si ha modificado esta mal, porque no existe el dept
			fail();
		}
		
		//Cogemos el departamento que sabemos que existe
		TDepartamento tDept2 = saDepartamento.mostrarDepartamento(1);
		//Lo modificamos
		tDept2.setNombre("I+D+C");
		boolean ok2 = saDepartamento.modificarDepartamento(tDept2);
		if(!ok2){ // si no ha modificado esta mal
			fail();
		}
	}
}
