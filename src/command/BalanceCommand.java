
package command;


public class BalanceCommand implements Command {
	private CommandGetBalance getCommand;
	private CommandSetBalance setCommand;

	public BalanceCommand(CommandGetBalance getCommand, CommandSetBalance setCommand) {
		this.getCommand = getCommand;
		this.setCommand = setCommand;
	}

	@Override
	public double getBalance() {
		return getCommand.getBalance();
	}

	@Override
	public void setBalance(double value) {
		setCommand.setBalance(value);
	}
}
