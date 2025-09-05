package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payloads.StorePOJO;
import io.restassured.response.Response;

public class StoreOperations {
	
	//CREATE A NEW ORDER
	public static Response CreateOrder(StorePOJO payload)
	{
		Response response =
		given()
			.log().all()
			.header("Content-Type","application/json")
			.body(payload)	
		.when()
			.post(API_Endpoints.place_order_post_url); // This is fetching data from API_Endpoints.java class
			
		
		return response;
	}
	
	//GET AN ORDER DETAILS
	public static Response GetOrder(Integer orderId)
	{
		Response response =
				
			given()
				.log().all()
				.header("Content-Type","application/json")
				.pathParam("orderId", orderId)
			.when()
				.get(API_Endpoints.find_order_get_url);// This is fetching data from API_Endpoints.java class
		
		return response;
	}
	
	
	//DELETE AN ORDER
	public static Response DeleteOrder(Integer orderId)
	{
		Response response =
				
				given()
					.log().all()
					.header("Content-Type","application/json")
					.pathParam("orderId", orderId)
				.when()
					.delete(API_Endpoints.delete_order_get_url);// This is fetching data from API_Endpoints.java class
			
			return response;
	}

}
