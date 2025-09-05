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
	
	
	public static Response FindPet(Integer petId)
	{
		Response response = 
				
		given()
			.log().all()
			.pathParam("petId", petId)
		
		.when()
			.get(pet_urls.getString("find_pet_get_url"));
		
		return response;
	}
	
	
	public static Response UpdatePet(PetPOJO payload)
	{
		Response response =
		
		given()
			.log().all()
			.header("Content-Type","application/json")
			.body(payload)
		.when()
			.put(pet_urls.getString("update_pet_put_url"));
		
		return response;
	}
	
	public static Response DeletePet(Integer petId)
	{
		Response response =
				
		given()
			.log().all()
			.pathParam("petId", petId)
		.when()
			.delete(pet_urls.getString("delete_pet_delete_url"));
		
		return response;
			
	}
	

}
