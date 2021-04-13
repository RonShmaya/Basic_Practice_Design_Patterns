
package command;

public class CommandSetBalance implements Command {

	private Balance balance;

	public CommandSetBalance(Balance balance) {
		this.balance = balance;
	}

	@Override
	public double getBalance() {
		return -1;
	}

	@Override
	public void setBalance(double value) {
		balance.set(value);
	}

}
