/**
 * 
 */
package presentacion.command.cliente;

import java.util.List;

import negocio.cliente.SACliente;
import negocio.cliente.TCliente;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandListarClientes implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		List<TCliente> lista = saCliente.listarClientes((boolean) context.getDato());
		c.setDato(lista);
		if (lista.isEmpty())
			c.setEvento(CommandEnum.ListarClientesFallo);
		else
			c.setEvento(CommandEnum.ListarClientesCorrecto);
		return c;
	}
}