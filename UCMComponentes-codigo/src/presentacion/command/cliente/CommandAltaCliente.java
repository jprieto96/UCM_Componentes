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

public class CommandAltaCliente implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		int id = saCliente.altaCliente((TCliente) context.getDato());
		c.setDato(id);
		if (id > 0)
			c.setEvento(CommandEnum.AltaClienteCorrecto);
		else
			c.setEvento(CommandEnum.AltaClienteFallo);
		return c;
	}
}