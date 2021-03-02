package negocio.material.bajaMaterial;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;

import negocio.factoria.FactoriaNegocio;
import negocio.material.Material;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioBajaMaterial {
	@BeforeClass
	public static void before(){
		//Partimos de que existe un material 
		TMaterial tMat = new TMaterial(1,"Ordenador",550.36,true);
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		saMaterial.altaMaterial(tMat);
	}
	@Test
	public void bajaMaterial(){
		//damos de baja ese Material que sabemos que tiene id = 1
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		boolean ok = saMaterial.bajaMaterial(1);

		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Recuperamos el Material para ver si esta dado de baja correctamente
		Material mat = em.find(Material.class, 1);
		assertEquals(false,mat.getActivo());
		assertEquals(true, ok);
		
	}
}
