/**
 * 
 */
package presentacion.command.factura;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandListarFacturas implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAFactura saFactura = FactoriaNegocio.getInstancia().generaSAFactura();
		List<TFactura> lista = saFactura.listarFactura((boolean) context.getDato());
		c.setDato(lista);
		if (lista.isEmpty())
			c.setEvento(CommandEnum.ListarFacturaError);
		else
			c.setEvento(CommandEnum.ListarFacturaCorrecto);
		return c;
	}
}