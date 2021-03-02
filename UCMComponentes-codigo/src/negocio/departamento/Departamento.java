package negocio.departamento;

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

import negocio.empleado.Empleado;
import negocio.inventario.Inventario;

import javax.persistence.NamedQueries;

// TODO revisar
@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.departamento.Departamento.findByid", query = "select dep from Departamento dep where :id = dep.id "),
		@NamedQuery(name = "negocio.departamento.Departamento.findAll", query = "select dep from Departamento dep"),
		@NamedQuery(name = "negocio.departamento.Departamento.findAllActives", query = "select dep from Departamento dep where dep.activo = true"),
		@NamedQuery(name = "negocio.departamento.Departamento.findBynombre", query = "select dep from Departamento dep where :nombre = dep.nombre "),
		@NamedQuery(name = "negocio.departamento.Departamento.findByversion", query = "select dep from Departamento dep where :version = dep.version "),
		@NamedQuery(name = "negocio.departamento.Departamento.findByEmpleados", query = "select obj from Empleado obj where :departamento = obj.departamento.id "),
		@NamedQuery(name = "negocio.departamento.Departamento.findByactivo", query = "select dep from Departamento dep where :activo = dep.activo ") })
public class Departamento implements Serializable {

	private static final long serialVersionUID = -3001252825301642627L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean activo;

	@OneToMany(mappedBy = "departamento")
	private List<Empleado> empleados;

	@OneToMany(mappedBy = "departamento")
	private List<Inventario> inventario;

	@Column(unique = true)
	private String nombre;

	@Version
	private int version;

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Departamento(int id) {
		this.id = id;
	}

	public Departamento(String nombre) {
		this.nombre = nombre;
	}

	public Departamento(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Departamento(String nombre, boolean activo) {
		this.nombre = nombre;
		this.activo = activo;
	}

	public Departamento() {
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

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
