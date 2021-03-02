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

public class CommandMostrarProveedor implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProveedor saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		Integer id = (Integer) context.getDato();
		TProveedor proveedor = saProveedor.mostrarProveedor(id);
		c.setDato(proveedor);
		if (proveedor != null)
			c.setEvento(CommandEnum.MostrarProveedorCorrecta);
		else
			c.setEvento(CommandEnum.MostrarProveedorFallo);
		return c;
	}
}