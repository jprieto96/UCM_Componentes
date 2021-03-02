/**
 * 
 */
package presentacion.command.inventario;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.inventario.SAInventario;
import negocio.inventario.TInventario;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandMostrarInventarioPorMaterial implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAInventario saInventario = FactoriaNegocio.getInstancia().generaSAInventario();
		TInventario i = (TInventario) context.getDato();
		List<TInventario> inv = saInventario.mostrarInventarioPorMaterial(i.getIdMaterial());
		c.setDato(inv);
		if (!inv.isEmpty())
			c.setEvento(CommandEnum.MostrarInventarioPorMaterialCorrecto);
		else
			c.setEvento(CommandEnum.MostrarInventarioPorMaterialFallo);
		return c;
	}
}