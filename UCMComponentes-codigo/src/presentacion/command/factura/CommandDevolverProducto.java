package presentacion.command.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;

import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandDevolverProducto implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAFactura saFactura = FactoriaNegocio.getInstancia().generaSAFactura();
		boolean exito = saFactura.devolverProducto((TFactura) context.getDato());
		c.setDato(exito);
		if (!exito)
			c.setEvento(CommandEnum.DevolverProductoFallo);
		else
			c.setEvento(CommandEnum.DevolverProductoCorrecto);
		return c;
	}

}
