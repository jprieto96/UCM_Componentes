/**
 * 
 */
package presentacion.command.material;

import negocio.factoria.FactoriaNegocio;
import negocio.material.Material;
import negocio.material.SAMaterial;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandBajaMaterial implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		boolean exito = saMaterial.bajaMaterial((int) context.getDato());
		c.setDato(exito);
		if (exito)
			c.setEvento(CommandEnum.BajaMaterialCorrecto);
		else
			c.setEvento(CommandEnum.BajaMaterialFallo);
		return c;
	}
}