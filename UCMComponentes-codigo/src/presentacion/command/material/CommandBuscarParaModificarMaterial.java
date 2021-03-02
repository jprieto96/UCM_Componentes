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

public class CommandBuscarParaModificarMaterial implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		int id = (int) context.getDato();
		TMaterial m = saMaterial.mostrarMaterial(id);
		c.setDato(m);
		if (m != null)
			c.setEvento(CommandEnum.BuscarParaModificarMaterialCorrecto);
		else
			c.setEvento(CommandEnum.BuscarParaModificarMaterialFallo);
		return c;
	}
}