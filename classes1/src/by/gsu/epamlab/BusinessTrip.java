package by.gsu.epamlab;

public class BusinessTrip {
	private static final int RATE = 1000;
	private String account;
	private int transport;
	private int days;
	
	public BusinessTrip() {
		transport = 0;
		days = 0;
	}
	
	public  BusinessTrip(String account, int transport, int days) {
		this.account = account;
		this.transport = transport;
		this.days = days;	
	}
	
	public int getDays() {
		return days;
	}
	
	public void setDays(int days) {
		this.days = days;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public int getTransport() {
		return transport;
	}
	
	public void setTransport(int transport) {
		this.transport = transport;
	}
	
	public int getTotal() {
		return  transport + RATE * days;
	}
	
	private String moneyToString(int money) {
		return String.format("%d.%02d", money/100, money%100);
	}

	public void show(){
		System.out.format("rate = %s%n", moneyToString(RATE));
		System.out.println("account = " + account);
		System.out.format("transport = %s%n", moneyToString(transport));
		System.out.println("days = " + days);
		System.out.format("total = %s%n", moneyToString(getTotal()));
	}
	@Override
	public String toString() {
		return String.format("%s;%s;%d;%s;", account, moneyToString(transport),
				days, moneyToString(getTotal()));
	}
}

