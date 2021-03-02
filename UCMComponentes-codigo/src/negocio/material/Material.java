/**
 * 
 */
package negocio.material;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import negocio.inventario.Inventario;

import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.material.Material.findByid", query = "select obj from Material obj where :id = obj.id "),
		@NamedQuery(name = "negocio.material.Material.findAll", query = "select obj from Material obj"),
		@NamedQuery(name = "negocio.material.Material.findAllActives", query = "select obj from Material obj where obj.activo = true"),
		@NamedQuery(name = "negocio.material.Material.findBynombre", query = "select obj from Material obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.material.Material.findByprecio", query = "select obj from Material obj where :precio = obj.precio "),
		@NamedQuery(name = "negocio.material.Material.findByversion", query = "select obj from Material obj where :version = obj.version "),
		@NamedQuery(name = "negocio.material.Material.findByactivo", query = "select obj from Material obj where :activo = obj.activo ") })
public class Material implements Serializable {

	private static final long serialVersionUID = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean activo;

	@OneToMany(mappedBy = "material")
	private List<Inventario> inventario;

	@Column(unique = true)
	private String nombre;
	private double precio;

	@Version
	private int version;

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Material(int id) {
		this.id = id;
	}

	public Material(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public Material(String nombre, double precio, boolean activo) {
		this.nombre = nombre;
		this.precio = precio;
		this.activo = activo;
	}

	public Material(int id, String nombre, double precio) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Material() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}