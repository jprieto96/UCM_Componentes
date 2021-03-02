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

public class CommandAltaEmpleado implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		int id = saEmpleado.altaEmpleado((TEmpleado) context.getDato());
		c.setDato(id);
		if (id > 0)
			c.setEvento(CommandEnum.AltaEmpleadoCorrecto);
		else
			c.setEvento(CommandEnum.AltaEmpleadoFallo);
		return c;
	}
}