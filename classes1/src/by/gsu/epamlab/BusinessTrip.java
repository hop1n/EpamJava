package by.gsu.epamlab;

public class BusinessTrip {
	public static final int RATE = 1000;
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

	public void show(){
		System.out.format("rate = %d.%02d%n", RATE/100, RATE%100);
		System.out.println("account = " + account);
		System.out.format("transport = %d.%02d%n",  transport/100, transport%100);
		System.out.println("days = " + days);
		System.out.format("total = %d.%02d%n", getTotal()/100, getTotal()%100);
	}
	@Override
	public String toString() {
		return String.format("%s;%d.%02d;%d;%d.%02d;", account, transport/100,
				transport%100, days, getTotal()/100, getTotal()%100);
	}
}
