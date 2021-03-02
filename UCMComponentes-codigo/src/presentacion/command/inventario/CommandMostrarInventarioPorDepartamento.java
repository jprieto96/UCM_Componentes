/**
 * 
 */
package presentacion.command.inventario;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandMostrarInventarioPorDepartamento implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		TInventario i = (TInventario) context.getDato();
		List<TInventario> inv = saInventario.mostrarInventarioPorDepartamento(i.getIdDepartamento());
		c.setDato(inv);
		if (!inv.isEmpty())
			c.setEvento(CommandEnum.MostrarInventarioPorDepartamentoCorrecto);
		else
			c.setEvento(CommandEnum.MostrarInventarioPorDepartamentoError);
		return c;
	}
}