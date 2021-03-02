package presentacion.command.producto;

import negocio.factoria.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandAltaProducto implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProducto saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		int id = saProducto.altaProducto((TProducto) context.getDato());
		c.setDato(id);
		if (id > 0)
			c.setEvento(CommandEnum.AltaProductoCorrecto);
		else
			c.setEvento(CommandEnum.AltaProductoFallo);
		return c;
	}
}
