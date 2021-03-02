/**
 * 
 */
package negocio.inventario;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import negocio.departamento.Departamento;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import negocio.material.Material;
import javax.persistence.EmbeddedId;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.Inventario.findByexistencias", query = "select obj from Inventario obj where :existencias = obj.existencias "),
		@NamedQuery(name = "negocio.Inventario.findBydepartamento", query = "select obj from Inventario obj where :departamento = obj.departamento "),
		@NamedQuery(name = "negocio.Inventario.findBymaterial", query = "select obj from Inventario obj where :material = obj.material "),
		@NamedQuery(name = "negocio.Inventario.findBydepartamentoID", query = "select obj from Inventario obj where :departamentoID = obj.id.departamentoID "),
		@NamedQuery(name = "negocio.Inventario.findAll", query = "select obj from Inventario obj"),
		@NamedQuery(name = "negocio.Inventario.findAllActives", query = "select obj from Inventario obj where obj.activo = true"),
		@NamedQuery(name = "negocio.Inventario.findBymaterialID", query = "select obj from Inventario obj where :materialID = obj.id.materialID ") })
public class Inventario implements Serializable {

	private static final long serialVersionUID = 0;

	@EmbeddedId
	private InventarioPK id;

	@ManyToOne
	@MapsId("departamentoID")
	private Departamento departamento;

	@ManyToOne
	@MapsId("materialID")
	private Material material;

	private int existencias;
	
	private boolean activo;
	
	@Version
	private int version;

	public Inventario() {}

	public Inventario(int departamento, int material, int existencias, boolean activo) {
		this.id = new InventarioPK(departamento, material);
		this.activo = activo;
		this.existencias = existencias;
	}

	public int getExistencias() {
		return this.existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public int getDepartamentoID() {
		return this.id.getDepartamentoID();
	}

	public void setDepartamentoID(int id) {
		this.id.setDepartamentoID(id);
	}

	public int getMaterialID() {
		return this.id.getMaterialID();
	}

	public void setMaterialID(int id) {
		this.id.setMaterialID(id);
	}

	public void setDepartamento(Departamento d) {
		this.departamento = d;
	}

	public void setMaterial(Material m) {
		this.material = m;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}