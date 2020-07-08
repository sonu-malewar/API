package stepDefination;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestData;
import resources.Utils;
import io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class PlaceSteps extends Utils {

	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	TestData data = new TestData();
	public static String place_id;
	APIResources resourcesAPI;

	@Given("^Add place payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload_with(String name, String address, String language) throws Throwable {

		request = given().spec(requestSpecification()).body(data.addPayload(name, address, language));
	}

	@When("^user got call \"([^\"]*)\" using \"([^\"]*)\" http method$")
	public void user_got_call_using_http_method(String resource, String method) throws Throwable {

		APIResources resourcesAPI = APIResources.valueOf(resource);
		System.out.println(resourcesAPI.getResource());

		if (method.equalsIgnoreCase("POST")) {

			response = request.when().post(resourcesAPI.getResource());
		} else if (method.equalsIgnoreCase("GET")) {

			response = request.when().get(resourcesAPI.getResource());
		} else if (method.equalsIgnoreCase("PUT")) {

			response = request.when().put(resourcesAPI.getResource());

		}

		res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();

	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int arg1) throws Throwable {

		assertEquals(response.getStatusCode(), 200);

	}

	@Then("^\"([^\"]*)\" in the response body is \"([^\"]*)\"$")
	public void in_the_response_body_is(String exp, String act) throws Throwable {

		assertEquals(getJsonPath(response, exp), act);

	}

	@Then("^verify place_id created map with \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_id_created_map_with_using(String expName, String resource) throws Throwable {

		place_id = getJsonPath(response, "place_id");

		request = given().spec(requestSpecification()).queryParam("place_id", place_id);

		user_got_call_using_http_method(resource, "GET");

		assertEquals(response.getStatusCode(), 200);

		String actName = getJsonPath(response, "name");

		assertEquals(expName, actName);

	}

	@Given("^User got call \"([^\"]*)\" using \"([^\"]*)\" http method with Update place payload$")
	public void user_got_call_using_http_method_with_Update_place_payload(String resource, String method) throws Throwable {

		request = given().spec(requestSpecification()).queryParam("key", "qaclick123").queryParam("place_id", place_id)
				.body(data.updatePayload(place_id));

		user_got_call_using_http_method(resource, "PUT");

	}

	@Then("^Address should be updated with status code (\\d+)$")
	public void address_should_be_updated_with_status_code(int statuscode) throws Throwable {

		assertEquals(response.getStatusCode(), 200);

	}

	@Then("^verify \"([^\"]*)\" in the response body is \"([^\"]*)\"$")
	public void verify_in_the_response_body_is(String msgKey, String expValue) throws Throwable {

		String actValue = getJsonPath(response, msgKey);

		assertEquals(actValue, expValue);

	}
	
	@Given("^User got call \"([^\"]*)\" using \"([^\"]*)\" http method with Delete place payload$")
	public void user_got_call_using_http_method_with_Delete_place_payload(String resource, String method) throws Throwable {
		
		request = given().spec(requestSpecification()).queryParam("key", "qaclick123").body(data.deletePayload(place_id));
		
		
		user_got_call_using_http_method(resource,"POST");
	   
	}

	@Then("^Place should be deleted for respective place_id$")
	public void place_should_be_deleted_for_respective_place_id() throws Throwable {
		
		assertEquals(response.getStatusCode(), 200);
	    
	}

	@Then("^verify response body \"([^\"]*)\" is \"([^\"]*)\"$")
	public void verify_response_body_is(String expValue, String actValue) throws Throwable {
		
		String actValue1= getJsonPath(response, expValue);

		assertEquals(actValue, actValue1);
		
	    
	}

}
