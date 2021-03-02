package presentacion.command.cliente;

import negocio.cliente.SACliente;
import negocio.cliente.TDatosCompletosCliente;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandBuscarParaModificarCliente implements Command {
	public Context execute(Context context) {
		Context c = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		Integer id = (Integer) context.getDato();
		TDatosCompletosCliente cliente = saCliente.mostrarCliente(id);
		c.setDato(cliente.getCliente());
		if (cliente != null)
			c.setEvento(CommandEnum.BuscarParaModificarClienteCorrecto);
		else
			c.setEvento(CommandEnum.BuscarParaModificarClienteFallo);
		return c;
	}
}