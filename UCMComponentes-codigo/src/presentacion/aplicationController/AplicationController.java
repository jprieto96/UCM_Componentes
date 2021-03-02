/**
 * 
 */
package presentacion.aplicationController;

import presentacion.aplicationController.imp.AplicationControllerImp;
import presentacion.command.Context;

public abstract class AplicationController {

	private static AplicationController instance;

	public synchronized static AplicationController getInstance() {
		if (instance == null)
			instance = new AplicationControllerImp();
		return instance;
	}

	public abstract void handle(Context context);

}