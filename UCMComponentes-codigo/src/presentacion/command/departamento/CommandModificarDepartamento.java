package presentacion.command.departamento;

import negocio.factoria.FactoriaNegocio;
import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandModificarDepartamento implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		boolean exito = saDepartamento.modificarDepartamento((TDepartamento) context.getDato());
		c.setDato(exito);
		if (exito)
			c.setEvento(CommandEnum.ModificarDepartamentoCorrecto);
		else
			c.setEvento(CommandEnum.ModificarDepartamentoFallo);
		return c;
	}
}
