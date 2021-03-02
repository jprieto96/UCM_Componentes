/**
 * 
 */
package presentacion.command.empleado;

import negocio.empleado.SAEmpleado;
import negocio.factoria.FactoriaNegocio;
import negocio.material.SAMaterial;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandBajaEmpleado implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		boolean exito = saEmpleado.bajaEmpleado((int) context.getDato());
		c.setDato(exito);
		if (exito)
			c.setEvento(CommandEnum.BajaEmpleadoCorrecto);
		else
			c.setEvento(CommandEnum.BajaEmpleadoFallo);
		return c;
	}
}