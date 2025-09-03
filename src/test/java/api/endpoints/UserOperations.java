package api.endpoints;
import static io.restassured.RestAssured.*;
import api.payloads.UserPOJO_payload;
import io.restassured.response.Response;

public class UserOperations {
	
	//CREATE USER
	public static Response CreateUser(UserPOJO_payload payload)
	{
		Response response =
		given()
		.log().all()
			.header("Content-Type","application/json")
			.body(payload)
		.when()
			.post(API_Endpoints.create_user_post__url);
		
		return response;
	}
	
	//GET USER
	public static Response GetUser(String userName)
	{
		Response response =
		given()
			.log().all()
			.header("Content-Type","application/json")
			.pathParam("username", userName)
		.when()
			.get(API_Endpoints.find_user_get_url);
		
		return response;
	}
		
	//UPDATE USER
	
	public static Response UpdateUser(UserPOJO_payload payload)
	{
		Response response =
		given()
			.log().all()
			.header("Content-Type","application/json")
			.pathParam("username", payload.getUserName())
			.body(payload)
		.when()
			.put(API_Endpoints.update_user_put_url);
		
		return response;
	}
	
	//DELETE USER
	public static Response DeleteUser(String userName)
	{
		Response response =
		given()
			.log().all()
			.header("Content-Type","application/json")
			.pathParam("username", userName)
		.when()
			.delete(API_Endpoints.user_delete_url);
		
		return response;
	}
	

}
