/**
 * 
 */
package presentacion.command.factory;

import presentacion.command.Command;
import presentacion.command.CommandEnum;
import presentacion.command.factory.imp.CommandFactoryImp;

public abstract class CommandFactory {

	private static CommandFactory instance;

	public synchronized static CommandFactory getInstance() {
		if (instance == null)
			instance = new CommandFactoryImp();
		return instance;
	}

	public abstract Command getCommand(CommandEnum command);
}