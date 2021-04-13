package Strore;


import java.time.LocalTime;

import command.BalanceCommand;

public class VCall extends Thread {
	public final int MINUTE = 60;
	private String simNumber;
	private String callNumber;
	private BalanceCommand balanceCommand;
	private double pricePerMin;

	public VCall(String callNumber, double pricePerMin) {
		this.callNumber = callNumber;
		this.pricePerMin = pricePerMin;
	}

	public String getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public double getSimBalance() {
		return balanceCommand.getBalance();
	}

	public void setSimBalance(double simBalance) {
		this.balanceCommand.setBalance(simBalance);
	}

	public double getPricePerMin() {
		return pricePerMin;
	}

	public void setPricePerMin(double pricePerMin) {
		this.pricePerMin = pricePerMin;
	}

	@Override
	public void run() {
		System.out.printf("Voice Call Started [From: %s To: %s Balance: %.2f]\n", getSimNumber(), callNumber,
				getSimBalance());
		LocalTime duration = LocalTime.of(0, 0, 0);
		while (balanceCommand.getBalance() - (pricePerMin / MINUTE) > 0) // if balance-secPrice > 0 you can speak
		{
			try {
				Thread.sleep(1000); // we are wait 1 second and check again
				balanceCommand.setBalance(balanceCommand.getBalance() - (pricePerMin / MINUTE)); // update balance
				duration = duration.plusSeconds(1); // add second he speak
			} catch (InterruptedException e) {
				System.out.printf("\nVoice Call Stopped [From: %s To: %s Balance: %.2f Duration: %s]\n", getSimNumber(),
						callNumber, balanceCommand.getBalance(), duration);
				return;
			}
		}
		System.out.printf("\nVoice Call Finished(No Enough Balance) [From: %s To: %s Balance: %.2f Duration: %s]\n",
				getSimNumber(), callNumber, balanceCommand.getBalance(), duration);
	}

	public void setBalanceCommand(BalanceCommand balanceCommand) {
		this.balanceCommand = balanceCommand;

	}

}
