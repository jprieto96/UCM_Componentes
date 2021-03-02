package presentacion.command.departamento;

import java.util.List;

import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.Context;

public class CommandNominaDepartamento implements Command{

	@Override
	public Context execute(Context context) {
		Context c = new Context();
		SADepartamento saDepartamento = FactoriaNegocio.getInstancia().generaSADepartamento();
		List<String> infNomina = saDepartamento.calculoNominaDepartamento((int) context.getDato());
		c.setDato(infNomina);
		if (infNomina.get(0) != "0.0")
			c.setEvento(CommandEnum.NominaDepartamentoCorrecto);
		else
			c.setEvento(CommandEnum.NominaDepartamentoFallo);
		return c;
	}

}
