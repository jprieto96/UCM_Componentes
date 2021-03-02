package negocio.material.mostrarMaterial;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioMostrarMaterial {
	@BeforeClass
	public static void before(){
		//Partimos de que existe un Material
		TMaterial tMat = new TMaterial(1,"Ordenador",550.36,true);
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		saMaterial.altaMaterial(tMat);
		
	}
	@Test
	public void mostrarMaterial(){
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		//mostramos el Material id = 1
		TMaterial tMat = saMaterial.mostrarMaterial(1);
		String precio = String.valueOf(tMat.getPrecio());
		//aseguramos que los valores son los correctos
		assertEquals(1,tMat.getId());
		assertEquals("Ordenador",tMat.getNombre());
		assertEquals(true,tMat.getActivo());
		assertEquals("550.36",precio);


	}
}
