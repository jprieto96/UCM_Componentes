package presentacion.command.departamento;

import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandBuscarParaModificarDepartamento implements Command {

	public Context execute(Context context) {
		Context c = new Context();
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		int id = (int) context.getDato();
		TDepartamento d = saDepartamento.mostrarDepartamento(id);
		c.setDato(d);
		if (d != null)
			c.setEvento(CommandEnum.BuscarParaModificarDepartamentoCorrecto);
		else
			c.setEvento(CommandEnum.BuscarParaModificarDepartamentoFallo);
		return c;
	}
}
