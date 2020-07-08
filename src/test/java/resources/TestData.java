package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {
	
	public AddPlace addPayload(String name,String address,String language)
	{
		AddPlace place= new AddPlace();
		place.setAccuracy(16);
		place.setAddress(address);
		place.setLanguage(language);
		place.setName(name);
		place.setPhone_number("9172311747");
		place.setWebsite("www.google.com");
		Location l = new Location();
		l.setLat(34.7891);
		l.setLng(-84.3654);
		place.setLocation(l);
		List<String> list = new ArrayList<String>();
		list.add("smartwatch");
		list.add("Braslate");
		place.setTypes(list);
		
		return place;
		
	}
	
	public String updatePayload(String place_id) {
		
		
	  return "{\r\n" + 
			"\"place_id\":\""+place_id+"\",\r\n" + 
			"\"address\":\"Shri house Thane\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}\r\n" + 
			"";
	}
	
	public String deletePayload(String place_id) {
		
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}\r\n" + 
				"" ;
		
		
	}

}
