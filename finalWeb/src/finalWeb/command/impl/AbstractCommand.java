package finalWeb.command.impl;

import finalCore.controle.Fachada;
import finalCore.core.IFachada;
import finalWeb.command.ICommand;

public abstract class AbstractCommand implements ICommand{
	protected IFachada fachada = new Fachada();
}
