/**
 * 
 */
package presentacion.command.inventario;

import negocio.factoria.FactoriaNegocio;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;

import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandMostrarInventario implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAInventario saInventario =FactoriaNegocio.getInstancia().generaSAInventario();
		TInventario i = (TInventario) context.getDato();
		TInventario inv = saInventario.mostrarInventario(i.getIdMaterial(), i.getIdDepartamento());
		c.setDato(inv);
		if (inv != null)
			c.setEvento(CommandEnum.MostrarInventarioCorrecto);
		else
			c.setEvento(CommandEnum.MostrarInventarioFallo);
		return c;
	}
}