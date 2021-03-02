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


public class CommandListarInventarios implements Command {
	
	public Context execute(Context context) {
		Context c = new Context();
		SAInventario saInventario =FactoriaNegocio.getInstancia().generaSAInventario();
		List<TInventario> inventarios = saInventario.listarInventarios((boolean) context.getDato());
		c.setDato(inventarios);
		if(!inventarios.isEmpty()){
			c.setEvento(CommandEnum.ListarInventariosCorrecto);
		}
		else{
			c.setEvento(CommandEnum.ListarInventariosFallo);
		}
		return c;
	}
}