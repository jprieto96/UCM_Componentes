/**
 * 
 */
package presentacion.command.proveedor;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandListarProveedor implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProveedor saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		List<TProveedor> proveedores = saProveedor.listarProveedores((boolean) context.getDato());
		c.setDato(proveedores);
		if (proveedores.isEmpty())
			c.setEvento(CommandEnum.ListarProveedoresIncorrecto);
		else
			c.setEvento(CommandEnum.ListarProveedorCorrecto);
		return c;
	}

}