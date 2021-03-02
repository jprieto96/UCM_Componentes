package presentacion.command.producto;

import negocio.producto.SAProducto;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandBajaProducto implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAProducto saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		int id = (int) context.getDato();
		boolean isOK = saProducto.bajaProducto(id);
		if (isOK)
			c.setEvento(CommandEnum.BajaProductoCorrecto);
		else
			c.setEvento(CommandEnum.BajaProductoFallo);
		return c;
	}
}
