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

public class CommandBuscarParaModificarEmpleado implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		Integer id = (Integer) context.getDato();
		TEmpleado empleado = saEmpleado.mostrarEmpleado(id);
		c.setDato(empleado);
		if (empleado != null)
			c.setEvento(CommandEnum.BuscarParaModificarEmpleadoCorrecto);
		else
			c.setEvento(CommandEnum.BuscarParaModificarEmpleadoFallo);
		return c;
	}
}