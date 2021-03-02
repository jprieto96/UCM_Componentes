/**
 * 
 */
package presentacion.command.cliente;

import negocio.cliente.SACliente;
import negocio.cliente.TCliente;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandModificarCliente implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		boolean ok = saCliente.modificarCliente((TCliente) context.getDato());
		if (ok)
			c.setEvento(CommandEnum.ModificarClienteCorrecto);
		else
			c.setEvento(CommandEnum.ModificarCLienteFallo);
		return c;
	}

}