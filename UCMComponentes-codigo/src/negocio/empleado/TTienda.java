package negocio.empleado;

public class TTienda extends TEmpleado {

	private String puesto;

	public TTienda() {
	}

	public TTienda(Integer id, String dni, String nombre, String apellidos, double salario, boolean activo,
			String puesto) {
		super(id, dni, nombre, apellidos, salario, activo);
		this.puesto = puesto;
	}

	public TTienda(Integer id, String dni, String nombre, String apellidos, double salario, TipoEmpleado tipo,
			boolean activo, String puesto, int idDep) {
		super(id, dni, nombre, apellidos, tipo, salario, activo, idDep);
		this.puesto = puesto;
	}

	public TTienda(String NIF, String nombre, String apellidos, double salario, TipoEmpleado tipo, boolean activo,
			String puesto) {
		super(NIF, nombre, apellidos, tipo, salario, activo);
		this.puesto = puesto;

	}

	public TTienda(int id) {
		super(id);
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Empleado toEntity() {
		return new Tienda(id, dni, nombre, apellidos, salario, tipo, activo, puesto);
	}

	@Override
	public String toString() {
		return super.toString() + " Puesto:\n   > " + puesto;
	}

}
