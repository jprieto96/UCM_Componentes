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

public class CommandMostrarFacturaPorCliente implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAFactura saFactura = FactoriaNegocio.getInstancia().generaSAFactura();
		Integer id = (Integer) context.getDato();
		List<TFactura> lista = saFactura.mostrarFacturaPorCliente(id);
		c.setDato(lista);
		if (!lista.isEmpty())
			c.setEvento(CommandEnum.MostrarFacturaPorClienteCorrecto);
		else
			c.setEvento(CommandEnum.MostrarFacturaPorClienteError);
		return c;
	}
}