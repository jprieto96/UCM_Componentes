package negocio.material.modificarMaterial;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioModificarMaterial {
	
	@BeforeClass
	public static void before(){
		//Partimos de un material
		TMaterial tMat = new TMaterial(1,"Ordenador",550.36,true);
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		saMaterial.altaMaterial(tMat);
			
	}
	@Test
	public void modificarMaterial(){
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		//Intentamos modificar un Material que no existe
		TMaterial tMat2 = new TMaterial(2,"Lapices",100.25,true);
		boolean ok = saMaterial.modificarMaterial(tMat2);
		if(ok){ // si ha modificado esta mal, porque no existe el dept
			fail();
		}
		
		//Cogemos el Material que sabemos que existe
		TMaterial tMat3 = saMaterial.mostrarMaterial(1);
		//Lo modificamos
		tMat3.setNombre("Ordenador de sobremesa");
		boolean ok2 = saMaterial.modificarMaterial(tMat3);
		if(!ok2){ // si no ha modificado esta mal
			fail();
		}
	}
}
