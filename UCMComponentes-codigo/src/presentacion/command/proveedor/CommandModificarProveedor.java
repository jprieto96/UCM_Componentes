/**
 * 
 */
package presentacion.command.proveedor;

import negocio.factoria.FactoriaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandModificarProveedor implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProveedor saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		boolean ok = saProveedor.modificarProveedor((TProveedor) context.getDato());
		if (ok)
			c.setEvento(CommandEnum.ModificarProveedorCorrecto);
		else
			c.setEvento(CommandEnum.ModificarProveedorIncorrecto);
		return c;
	}
}