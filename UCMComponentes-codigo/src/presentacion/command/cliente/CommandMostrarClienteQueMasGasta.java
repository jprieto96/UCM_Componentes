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

public class CommandMostrarClienteQueMasGasta implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		TCliente cliente = saCliente.clienteQueMasGasta();
		if (cliente != null) {
			c.setDato(cliente);
			c.setEvento(CommandEnum.MostrarClienteQueMasGastaCorrecto);

		} else
			c.setEvento(CommandEnum.MostrarClienteQueMasGastaFallo);
		return c;
	}
}