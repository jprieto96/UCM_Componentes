/**
 * 
 */
package presentacion.dispatcher.imp;

import presentacion.command.Context;
import presentacion.dispatcher.Dispatcher;
import presentacion.main.Vista;

public class DispatcherImp extends Dispatcher {

	public void update(Context context) {
		Vista.getInstancia().update(context);
	}

}