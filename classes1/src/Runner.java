import by.gsu.epamlab.BusinessTrip;

public class Runner {

	public static void main(String[] args) {
		BusinessTrip[] businessTrips = {
				new BusinessTrip("Milena Asmolouskaya", 2000, 4),
				new BusinessTrip("Katerina Simanovich", 2000, 5),
				null,
				new BusinessTrip("Bahdan Savelyeu", 2000, 8),
				new BusinessTrip()
		};
		int maxTotal = businessTrips[0].getTotal();
		BusinessTrip maxTotalTrip = null;
		for (BusinessTrip businessTrip : businessTrips) {
			if (businessTrip != null) {
				businessTrip.show(); 
				if (businessTrip.getTotal() > maxTotal) {
					maxTotal = businessTrip.getTotal();
					maxTotalTrip = businessTrip;
				}	
			}			
		}
		System.out.println(maxTotalTrip);
		businessTrips[businessTrips.length-1].setTransport(12345);		
		System.out.println("Duration =" + (businessTrips[0].getDays()
				+ businessTrips[1].getDays()));
		for(BusinessTrip businessTrip : businessTrips){
			if (businessTrip != null) {
				System.out.println(businessTrip);
			}
		}
		
	}

}
