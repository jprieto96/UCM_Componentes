/**
 * 
 */
package presentacion.command.empleado;

import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandModificarEmpleado implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		boolean ok = saEmpleado.modificarEmpleado((TEmpleado) context.getDato());
		if (ok)
			c.setEvento(CommandEnum.ModificarEmpleadoCorrecto);
		else
			c.setEvento(CommandEnum.ModificarEmpleadoFallo);
		return c;
	}

}