/**
 * 
 */
package negocio.cliente;

public class TEmpresa extends TCliente {

	private int idEmpresa;
	private String direccionSocial;

	public TEmpresa(int id, String nombre, String cuentaBancaria, boolean activo, String NIF) {
		super(id, nombre, cuentaBancaria, activo, NIF);
	}

	public TEmpresa(int id, String nombre, String cuentaBancaria, boolean activo, String NIF, int idEmpresa,
			String direccionSocial) {
		super(id, nombre, cuentaBancaria, activo, NIF);
		this.idEmpresa = idEmpresa;
		this.direccionSocial = direccionSocial;

	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getDireccionSocial() {
		return direccionSocial;
	}

	public void setDireccionSocial(String direccionSocial) {
		this.direccionSocial = direccionSocial;
	}

	@Override
	public String toString() {
		return "DATOS DEL CLIENTE\n" + " Identificador cliente:\n   > " + id + "\n" + " Identificador empresa:\n   > "
				+ idEmpresa + "\n" + " Nombre completo:\n   > " + nombre + "\n" + " Cuenta Bancaria:\n   > "
				+ cuentaBancaria + "\n" + " NIF:\n   > " + NIF + "\n" + " Direccion Social:\n   > " + direccionSocial
				+ "\n" + " Estado actual:\n   > " + (!activo ? "NO " : "") + "ACTIVO";
	}

}