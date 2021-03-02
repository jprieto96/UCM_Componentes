package presentacion.main;

import presentacion.command.Context;

public class VistaImp extends Vista {

	private VistaGeneral vistaGeneral;

	public VistaImp() {
		vistaGeneral = new VistaGeneral();
	}

	public void update(Context context) {
		vistaGeneral.update(context);
	}

}
