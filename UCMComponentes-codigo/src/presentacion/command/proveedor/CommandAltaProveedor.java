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

public class CommandAltaProveedor implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProveedor saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		int id = saProveedor.altaProveedor((TProveedor) context.getDato());
		c.setDato(id);
		if (id > 0)
			c.setEvento(CommandEnum.AltaProveedorCorrecta);
		else
			c.setEvento(CommandEnum.AltaProveedorFallo);
		return c;

	}

}