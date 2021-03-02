/**
 * 
 */
package presentacion.command.material;

import negocio.factoria.FactoriaNegocio;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandAltaMaterial implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		int id = -1;
		id = saMaterial.altaMaterial((TMaterial) context.getDato());
		c.setDato(id);
		if (id > 0)
			c.setEvento(CommandEnum.AltaMaterialCorrecto);
		else
			c.setEvento(CommandEnum.AltaMaterialFallo);
		return c;
	}
}