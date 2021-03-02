/**
 * 
 */
package presentacion.aplicationController.imp;

import presentacion.aplicationController.AplicationController;
import presentacion.command.Command;
import presentacion.command.Context;
import presentacion.command.factory.CommandFactory;
import presentacion.dispatcher.Dispatcher;

public class AplicationControllerImp extends AplicationController {

	public void handle(Context context) {
		Command command = CommandFactory.getInstance().getCommand(context.getEvento());
		if (command != null) {
			Context evNegocio = command.execute(context);
			Dispatcher.getInstancia().update(evNegocio);
		} else
			Dispatcher.getInstancia().update(context);
	}

}