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

public class CommandAltaInventario implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		boolean ok = saInventario.altaInventario((TInventario) context.getDato());
		c.setDato(ok);
		if (ok)
			c.setEvento(CommandEnum.AltaInventarioCorrecto);
		else
			c.setEvento(CommandEnum.AltaInventarioError);
		return c;
	}
	
}