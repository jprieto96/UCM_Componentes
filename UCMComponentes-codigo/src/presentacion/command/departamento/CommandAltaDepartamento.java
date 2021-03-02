package presentacion.command.departamento;

import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandAltaDepartamento implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		int id = -1;
		id = saDepartamento.altaDepartamento((TDepartamento) context.getDato());
		c.setDato(id);
		if (id > 0)
			c.setEvento(CommandEnum.AltaDepartamentoCorrecto);
		else
			c.setEvento(CommandEnum.AltaDepartamentoFallo);
		return c;
	}
}
