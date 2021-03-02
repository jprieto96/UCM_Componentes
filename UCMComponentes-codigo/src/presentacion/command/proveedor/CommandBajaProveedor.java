/**
 * 
 */
package presentacion.command.proveedor;

import negocio.factoria.FactoriaNegocio;
import negocio.proveedor.SAProveedor;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandBajaProveedor implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProveedor saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		int id = (int) context.getDato();
		boolean isOK = saProveedor.bajaProveedor(id);
		if (isOK)
			c.setEvento(CommandEnum.BajaProveedorCorrecta);
		else
			c.setEvento(CommandEnum.BajaProveedorFallo);
		return c;
	}
}