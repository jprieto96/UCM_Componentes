package negocio.material.altaMaterial;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;
import negocio.material.Material;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;

public class TestNegocioAltaMaterial {
	
	
	@Test
	public void altaMaterial(){
		
		TMaterial tMat = new TMaterial(1, "Ordenador", 550.36, true);
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		int id = saMaterial.altaMaterial(tMat);
		EntityManagerFactory emf = SingletonEntityManagerFactory.getInstancia().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Material mat = em.find(Material.class,id);
		String precio = String.valueOf(mat.getPrecio());
		//tiene que ser el mismo ID,NOMBRE, ACTIVO, PRECIO
		assertEquals(id,mat.getId());
		assertEquals(tMat.getNombre(),mat.getNombre());
		assertEquals(tMat.getActivo(),mat.getActivo());
		assertEquals("550.36",precio);

		//Reactivado de material
		saMaterial.bajaMaterial(id);
		TMaterial tMat2 = saMaterial.mostrarMaterial(id);
		//Volvemos a dar de alta el material, tiene que tener el mismo id, ya que es una reactivacion
		int id2 = saMaterial.altaMaterial(tMat2);
		em.getTransaction().commit();

		assertEquals(id,id2);

		em.close();
	}
}
