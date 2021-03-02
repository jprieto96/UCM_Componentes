/**
 * 
 */
package presentacion.command.inventario;

import negocio.factoria.FactoriaNegocio;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandBajaInventario implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		TInventario in = (TInventario) context.getDato();
		boolean exito = saInventario.bajaInventario(in.getIdMaterial(), in.getIdDepartamento());
		c.setDato(exito);
		if (exito)
			c.setEvento(CommandEnum.BajaInventarioCorrecto);
		else
			c.setEvento(CommandEnum.BajaInventarioError);
		return c;
	}
	
}