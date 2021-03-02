/**
 * 
 */
package presentacion.command.factory.imp;

import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.factory.CommandFactory;

public class CommandFactoryImp extends CommandFactory {

	public Command getCommand(CommandEnum command) {
		return command.getComando();
	}

}