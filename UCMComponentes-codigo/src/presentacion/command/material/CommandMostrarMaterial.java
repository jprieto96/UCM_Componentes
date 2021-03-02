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

public class CommandMostrarMaterial implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		TMaterial m = saMaterial.mostrarMaterial((int) context.getDato());
		c.setDato(m);
		if (m != null)
			c.setEvento(CommandEnum.MostrarMaterialCorrecto);
		else
			c.setEvento(CommandEnum.MostrarMaterialFallo);
		return c;
	}
}