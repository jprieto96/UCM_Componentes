/**
 * 
 */
package presentacion.command.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandAltaFactura implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAFactura saFactura = FactoriaNegocio.getInstancia().generaSAFactura();
		int id = saFactura.altaFactura((TFactura) context.getDato());
		c.setDato(id);
		if (id > 0)
			c.setEvento(CommandEnum.AltaFacturaCorrecto);
		else
			c.setEvento(CommandEnum.AltaFacturaFallo);
		return c;
	}
}