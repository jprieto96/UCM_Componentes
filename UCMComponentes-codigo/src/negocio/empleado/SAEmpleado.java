/**
 * 
 */
package negocio.empleado;

import java.util.List;

public interface SAEmpleado {

	public int altaEmpleado(TEmpleado empleado);

	public boolean bajaEmpleado(int id);

	public TEmpleado mostrarEmpleado(int id);

	public boolean modificarEmpleado(TEmpleado empleado);

	public List<TEmpleado> listarEmpleados(boolean activo);

	public List<TEmpleado> mostrarEmpleadosPorDepartamento(int id);
	
}