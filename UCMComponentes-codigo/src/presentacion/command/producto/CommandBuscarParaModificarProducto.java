package presentacion.command.producto;

import negocio.factoria.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandBuscarParaModificarProducto implements Command {

	@Override
	public Context execute(Context context) {
		Context c = new Context();
		SAProducto saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		Integer id = (Integer) context.getDato();
		TProducto producto = saProducto.mostrarProducto(id);
		c.setDato(producto);
		if (producto != null)
			c.setEvento(CommandEnum.BuscarParaModificarProductoCorrecto);
		else
			c.setEvento(CommandEnum.BuscarParaModificarProductoFallo);
		return c;
	}

}
