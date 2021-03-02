/**
 * 
 */
package presentacion.command;

public class Context {

	private Object dato;
	private CommandEnum evento;

	public Context() {
	}

	public Context(Object dato, CommandEnum evento) {
		this.dato = dato;
		this.evento = evento;
	}

	public Object getDato() {
		return dato;
	}

	public void setDato(Object dato) {
		this.dato = dato;
	}

	public CommandEnum getEvento() {
		return evento;
	}

	public void setEvento(CommandEnum evento) {
		this.evento = evento;
	}

}