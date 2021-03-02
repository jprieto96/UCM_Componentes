package presentacion.command.departamento;

import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandMostrarDepartamento implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		TDepartamento d = saDepartamento.mostrarDepartamento((int) context.getDato());
		c.setDato(d);
		if (d != null)
			c.setEvento(CommandEnum.MostrarDepartamentoCorrecto);
		else
			c.setEvento(CommandEnum.MostrarDepartamentoFallo);
		return c;
	}
}
