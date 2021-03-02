/**
 * 
 */
package negocio.empleado;

import javax.persistence.Entity;

import java.io.Serializable;
import javax.persistence.NamedQuery;

import negocio.departamento.Departamento;

import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.empleado.Repartidor.findByid", query = "select obj from Repartidor obj where :id = obj.id "),
		@NamedQuery(name = "negocio.empleado.Repartidor.findByzonaReparto", query = "select obj from Repartidor obj where :zonaReparto = obj.zonaReparto ") })
public class Repartidor extends Empleado implements Serializable {

	private static final long serialVersionUID = 0;

	private String zonaReparto;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int getVersion() {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param version
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setVersion(int version) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public Repartidor() {
	}

	public Repartidor(Integer id, String dni, String nombre, String apellidos, double salario, boolean activo,
			String zonaReparto) {
		super(id, dni, nombre, apellidos, salario, activo);
		this.zonaReparto = zonaReparto;
	}

	public Repartidor(Integer id, String dni, String nombre, String apellidos, double salario, TipoEmpleado tipo,
			boolean activo, String zonaReparto) {
		super(id, dni, nombre, apellidos, salario, tipo, activo);
		this.zonaReparto = zonaReparto;
	}

	public Repartidor(Integer id, String dni, String nombre, String apellidos, double salario, TipoEmpleado tipo,
			boolean activo, String zonaReparto, Departamento departamento) {
		super(id, dni, nombre, apellidos, salario, tipo, activo, departamento);
		this.zonaReparto = zonaReparto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZonaReparto() {
		return zonaReparto;
	}

	public void setZonaReparto(String zonaReparto) {
		this.zonaReparto = zonaReparto;
	}

	public TEmpleado toTransfer() {
		return new TRepartidor(id, dni, nombre, apellidos, salario, tipo, activo, zonaReparto, departamento.getId());
	}

	@Override
	public double calcularNomina() {
		return this.salario + 150; // Ejemplo de cálculo de salario polimórfico, sumamos los gastos relacionados al transporte
	}
}