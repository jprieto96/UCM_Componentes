/**
 * 
 */
package negocio.cliente;

public class TParticular extends TCliente {

	private int idParticular;
	private boolean fiel;

	public TParticular(int id, String nombre, String cuentaBancaria, boolean activo, String NIF) {
		super(id, nombre, cuentaBancaria, activo, NIF);
	}

	public TParticular(int id, String nombre, String cuentaBancaria, boolean activo, String NIF, int idParticular,
			boolean esFiel) {
		super(id, nombre, cuentaBancaria, activo, NIF);
		this.idParticular = idParticular;
		this.fiel = esFiel;
	}

	public int getIdParticular() {
		return idParticular;
	}

	public void setIdParticular(int idParticular) {
		this.idParticular = idParticular;
	}

	public boolean isFiel() {
		return fiel;
	}

	public void setFiel(boolean fiel) {
		this.fiel = fiel;
	}

	@Override
	public String toString() {
		return "DATOS DEL CLIENTE\n" + " Identificador cliente:\n   > " + id + "\n"
				+ " Identificador particular:\n   > " + idParticular + "\n" + " Nombre completo:\n   > " + nombre + "\n"
				+ " Cuenta Bancaria:\n   > " + cuentaBancaria + "\n" + " NIF:\n   > " + NIF + "\n" + " Fiel:\n   > "
				+ (!fiel ? "NO " : "SI") + "\n" + " Estado actual:\n   > " + (!activo ? "NO " : "") + "ACTIVO";
	}

}