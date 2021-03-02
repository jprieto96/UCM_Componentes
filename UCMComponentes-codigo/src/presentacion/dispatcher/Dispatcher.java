/**
 * 
 */
package presentacion.dispatcher;

import presentacion.command.Context;
import presentacion.dispatcher.imp.DispatcherImp;

public abstract class Dispatcher {

	private static Dispatcher instancia;

	public synchronized static Dispatcher getInstancia() {
		if (instancia == null)
			instancia = new DispatcherImp();
		return instancia;
	}

	public abstract void update(Context context);

}