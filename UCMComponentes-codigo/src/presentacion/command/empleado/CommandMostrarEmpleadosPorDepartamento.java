/**
 * 
 */
package presentacion.command.empleado;

import java.util.List;

import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandMostrarEmpleadosPorDepartamento implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAEmpleado saEmpleado = FactoriaNegocio.getInstancia().generaSAEmpleado();
		List<TEmpleado> lista = saEmpleado.mostrarEmpleadosPorDepartamento((Integer) context.getDato());
		c.setDato(lista);
		if (lista.isEmpty())
			c.setEvento(CommandEnum.ListarEmpleadosPorDepartamentoError);
		else
			c.setEvento(CommandEnum.ListarEmpleadosPorDepartamentoCorrecto);
		return c;
	}
}