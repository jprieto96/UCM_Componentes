/**
 * 
 */
package presentacion.command.material;

import negocio.factoria.FactoriaNegocio;
import negocio.material.Material;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandModificarMaterial implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		boolean exito = saMaterial.modificarMaterial((TMaterial) context.getDato());
		c.setDato(exito);
		if (exito)
			c.setEvento(CommandEnum.ModificarMaterialCorrecto);
		else
			c.setEvento(CommandEnum.ModificarMaterialFallo);
		return c;
	}
}