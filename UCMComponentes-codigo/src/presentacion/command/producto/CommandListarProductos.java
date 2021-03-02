package presentacion.command.producto;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandListarProductos implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProducto saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		List<TProducto> productos = saProducto.listarProductos((boolean) context.getDato());
		c.setDato(productos);
		if (productos.isEmpty())
			c.setEvento(CommandEnum.ListarProductoFallo);
		else
			c.setEvento(CommandEnum.ListarProductoCorrecto);
		return c;
	}
}
