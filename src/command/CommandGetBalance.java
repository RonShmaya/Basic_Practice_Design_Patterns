
package command;

public class CommandGetBalance implements Command {
	private Balance balance;

	public CommandGetBalance(Balance balance) {
		this.balance = balance;
	}

	@Override
	public double getBalance() {
		return this.balance.get();
	}

	@Override
	public void setBalance(double value) {
		// do nothing
	}

}
