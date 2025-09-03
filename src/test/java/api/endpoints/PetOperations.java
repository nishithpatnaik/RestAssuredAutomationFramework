package api.endpoints;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payloads.PetPOJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PetOperations {
	
	private static ResourceBundle pet_urls = ResourceBundle.getBundle("API_Endpoints");
	
	public static Response AddPet(PetPOJO payload)
	{
		
		Response response =
		
		given()
			.log().all()
			.header("Content-Type","application/json")
			.body(payload)
		.when()
			.post(pet_urls.getString("new_pet_post_url"));
		/*
		 * .then() .statusCode(200).extract().response();
		 */
		
		return response;
	}
	

}
