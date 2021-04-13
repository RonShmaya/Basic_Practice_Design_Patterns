package Strore;



import command.Balance;
import command.BalanceCommand;
import command.CommandGetBalance;
import command.CommandSetBalance;
import observer.Message;
import observer.Receiver;
import observer.Sender;

public class Mobile implements Sender, Receiver, MobileFunc {
	private String name;
	private String simNumber;
	private BalanceCommand balanceCommand;
	private VCall currentVCall;

	public Mobile(String name, String simNumber) {
		this.name = name;
		this.simNumber = simNumber;
		Balance balance = new Balance();
		this.balanceCommand = new BalanceCommand(new CommandGetBalance(balance), new CommandSetBalance(balance));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getSimBalance() {
		return balanceCommand.getBalance();
	}

	@Override
	public void setSimBalance(double simBalance) {
		balanceCommand.setBalance(simBalance);
	}

	@Override
	public VCall getCurrentVCall() {
		return currentVCall;
	}

	@Override
	public void startVCall(VCall vCall) {
		currentVCall = vCall;
		currentVCall.setSimNumber(simNumber);
		currentVCall.setBalanceCommand(balanceCommand);
		currentVCall.start();
	}

	@Override
	public void stopVCall() {
		currentVCall.interrupt();
	}

	@Override
	public void receiveMSG(Sender s, Message msg) {
		System.out.println("\n-------------" + this.simNumber + "-------------\n" + "New Message Has Arrived\n"
				+ "------------------------------------\n" + "From: " + s.getSimNumber() + "\n" + msg
				+ "------------------------------------\n");
	}

	@Override
	public String getSimNumber() {
		return simNumber;
	}

	@Override
	public void sendMSG(Receiver r, Message msg) {
		r.receiveMSG(this, msg);
	}

}
