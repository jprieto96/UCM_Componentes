package presentacion.command.producto;

import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandMostrarProducto implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProducto saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		Integer id = (Integer) context.getDato();
		TProducto producto = saProducto.mostrarProducto(id);
		c.setDato(producto);
		if (producto != null)
			c.setEvento(CommandEnum.MostrarProductoCorrecto);
		else
			c.setEvento(CommandEnum.MostrarProductoFallo);
		return c;
	}
}
