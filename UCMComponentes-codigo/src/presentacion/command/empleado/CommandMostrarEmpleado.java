/**
 * 
 */
package presentacion.command.empleado;

import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandMostrarEmpleado implements Command {

	public Context execute(Context context) {

		Context c = new Context();
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		TEmpleado e = saEmpleado.mostrarEmpleado((int) context.getDato());
		c.setDato(e);
		if (e != null)
			c.setEvento(CommandEnum.MostrarEmpleadoCorrecto);
		else
			c.setEvento(CommandEnum.MostrarEmpleadoFallo);
		return c;

	}
}