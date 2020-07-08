package resources;

public enum APIResources {
	
	Addplace("/maps/api/place/add/json"),
	getPlace("/maps/api/place/get/json"),
	putPLace("/maps/api/place/update/json"),
	deleteplace("/maps/api/place/delete/json");
	
	private String resource;
	
	APIResources (String resource){
		
		this.resource=resource;
		
	}
	
	public String getResource(){
		
		return resource;
	}

}
