/**
 * 
 */
package presentacion.command.cliente;

import negocio.cliente.SACliente;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandBajaCliente implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		int id = (int) context.getDato();
		boolean isOK = saCliente.bajaCliente(id);
		if (isOK)
			c.setEvento(CommandEnum.BajaClienteCorrecto);
		else
			c.setEvento(CommandEnum.BajaClienteFallo);
		return c;
	}
}