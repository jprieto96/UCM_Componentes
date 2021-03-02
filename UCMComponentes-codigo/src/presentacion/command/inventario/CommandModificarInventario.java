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

public class CommandModificarInventario implements Command {
	
	public Context execute(Context context) {
		Context c = new Context();
		SAInventario saInventario =FactoriaNegocio.getInstancia().generaSAInventario();
		boolean exito = saInventario.modificarInventario((TInventario) context.getDato());
		c.setDato(exito);
		if (exito)
			c.setEvento(CommandEnum.ModificarInventarioCorrecto);
		else
			c.setEvento(CommandEnum.ModificarInventarioFallo);
		return c;
	}
}