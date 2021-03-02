package negocio.material.listarMateriales;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


import negocio.factoria.FactoriaNegocio;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioListarMateriales {
	@BeforeClass
	public static void before(){
		//Partimos de que existen 3 Materials 
		TMaterial tMat = new TMaterial(1,"Ordenador",550.36,true);
		TMaterial tMat2 = new TMaterial(2,"Pizarra",170.78,true);
		TMaterial tMat3 = new TMaterial(3,"Altavoz",78.95,true);
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		saMaterial.altaMaterial(tMat);
		saMaterial.altaMaterial(tMat2);
		saMaterial.altaMaterial(tMat3);

	}
	@Test
	public void listarMateriales(){
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		//Damos de baja al Material id = 3
		boolean ok = saMaterial.bajaMaterial(3);
		//listamos todos los Materials
		List<TMaterial> listMat = saMaterial.listarMateriales(false);
		//Me deberia devolver 3
		assertEquals(3,listMat.size());
		//Listamos solo los activos
		List<TMaterial> listMatActivos = saMaterial.listarMateriales(true);
		//Me deberia devolver 2
		assertEquals(2, listMatActivos.size());
		
	}
}
