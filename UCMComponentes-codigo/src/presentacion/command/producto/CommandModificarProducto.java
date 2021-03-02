package presentacion.command.producto;

import negocio.factoria.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandModificarProducto implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProducto saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		boolean ok = saProducto.modificarProducto((TProducto) context.getDato());
		if (ok)
			c.setEvento(CommandEnum.ModificarProductoCorrecto);
		else
			c.setEvento(CommandEnum.ModificarProductoFallo);
		return c;
	}
}
