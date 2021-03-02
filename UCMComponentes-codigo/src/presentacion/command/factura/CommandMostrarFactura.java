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

public class CommandMostrarFactura implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAFactura saFactura = FactoriaNegocio.getInstancia().generaSAFactura();
		Integer id = (Integer) context.getDato();
		TFactura factura = saFactura.mostrarFactura(id);
		c.setDato(factura);
		if (factura != null)
			c.setEvento(CommandEnum.MostrarFacturaCorrecto);
		else
			c.setEvento(CommandEnum.MostrarFacturaError);
		return c;
	}
}