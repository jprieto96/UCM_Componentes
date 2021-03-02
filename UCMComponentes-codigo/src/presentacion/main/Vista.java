package presentacion.main;

import presentacion.command.Context;

public abstract class Vista {

	private static Vista instancia;

	public static Vista getInstancia() {
		if (instancia == null)
			instancia = new VistaImp();
		return instancia;
	}

	public abstract void update(Context context);
}
