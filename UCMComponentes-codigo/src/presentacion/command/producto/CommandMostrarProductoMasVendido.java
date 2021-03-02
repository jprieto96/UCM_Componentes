package presentacion.command.producto;

import negocio.factoria.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandMostrarProductoMasVendido implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProducto saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		TProducto producto = saProducto.productoMasVendido();
		if (producto != null) {
			c.setDato(producto);
			c.setEvento(CommandEnum.MostrarProductoMasVendidoCorrecto);

		} else
			c.setEvento(CommandEnum.MostrarProductoMasVendidoFallo);
		return c;
	}
}
