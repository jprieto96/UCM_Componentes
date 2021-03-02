package presentacion.command.factura;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.TFactura;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandAddProductoFactura implements Command {

	@Override
	public Context execute(Context context) {
		Context c = new Context();
		SAProducto saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		TFactura factura = (TFactura) context.getDato();
		TProducto producto = saProducto.mostrarProducto(factura.getIdProducto());
		c.setDato(producto);
		if (producto != null && producto.getExistencias() >= factura.getCantidad())
			c.setEvento(CommandEnum.AñadirProductoFacturaCorrecto);
		else
			c.setEvento(CommandEnum.AñadirProductoFacturaError);
		return c;
	}

}
