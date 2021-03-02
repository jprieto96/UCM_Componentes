/**
 * 
 */
package presentacion.command.material;

import java.util.List;

import negocio.factoria.FactoriaNegocio;
import negocio.material.Material;
import negocio.material.SAMaterial;
import negocio.material.TMaterial;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandListarMateriales implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SAMaterial saMaterial = FactoriaNegocio.getInstancia().generaSAMaterial();
		List<TMaterial> materiales = saMaterial.listarMateriales((boolean) context.getDato());
		c.setDato(materiales);
		if (!materiales.isEmpty())
			c.setEvento(CommandEnum.ListarMaterialesCorrecto);
		else
			c.setEvento(CommandEnum.ListarMaterialesFallo);
		return c;
	}
}