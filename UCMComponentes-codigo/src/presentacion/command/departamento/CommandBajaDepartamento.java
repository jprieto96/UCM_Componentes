package presentacion.command.departamento;

import negocio.factoria.FactoriaNegocio;
import negocio.departamento.SADepartamento;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandBajaDepartamento implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		boolean exito = saDepartamento.bajaDepartamento((int) context.getDato());
		c.setDato(exito);
		if (exito)
			c.setEvento(CommandEnum.BajaDepartamentoCorrecto);
		else
			c.setEvento(CommandEnum.BajaDepartamentoFallo);
		return c;
	}
}
