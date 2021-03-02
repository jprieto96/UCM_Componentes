package presentacion.command.departamento;

import java.util.List;

import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandListarDepartamentos implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		List<TDepartamento> departamentos = saDepartamento.listarDepartamentos((boolean) context.getDato());
		c.setDato(departamentos);
		if (!departamentos.isEmpty())
			c.setEvento(CommandEnum.ListarDepartamentosCorrecto);
		else
			c.setEvento(CommandEnum.ListarDepartamentosFallo);
		return c;
	}
}
