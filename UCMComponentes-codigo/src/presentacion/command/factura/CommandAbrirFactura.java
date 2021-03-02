package presentacion.command.factura;

import negocio.cliente.SACliente;
import negocio.cliente.TDatosCompletosCliente;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandAbrirFactura implements Command {

	@Override
	public Context execute(Context context) {
		Context c = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		Integer id = (Integer) context.getDato();
		TDatosCompletosCliente cliente = saCliente.mostrarCliente(id);
		if (cliente != null && cliente.getCliente().isActivo()) {
			c.setDato(cliente.getCliente());
			c.setEvento(CommandEnum.AbrirFacturaCorrecto);
		} else
			c.setEvento(CommandEnum.AbrirFacturaError);
		return c;
	}

}
