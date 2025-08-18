package api.endpoints;
import static io.restassured.RestAssured.*;
import java.util.ResourceBundle;
import api.payloads.StorePOJO_payload;
import io.restassured.response.Response;

//THIS WAS CREATED TO REPLICATE OPERATIONS BUT WHILE READING URL END POINTS FROM THE API_Endpoints.properties file

public class StoreOperations2 {
	
	private static ResourceBundle store_urls = ResourceBundle.getBundle("API_Endpoints"); //This loads the API_Endpoints.properties file

	
	
	//CREATE A NEW ORDER
	public static Response CreateOrder(StorePOJO_payload payload)
	{
		//String placeOrderURL = getURL().getString("place_order_post_url");
		Response response =
		given()
			.log().all()
			.header("Content-Type","application/json")
			.body(payload)	
		.when()
			//.post(API_Endpoints.place_order_post_url); // This is fetching data from API_Endpoints.java class
			.post(store_urls.getString("place_order_post_url")); // This is directly fetching the url endpoints from the API_Endpoints.properties file
		
		return response;
	}
	
	//GET AN ORDER DETAILS
	public static Response GetOrder(Integer orderId)
	{
		//String getOrder = getURL().getString("find_order_get_url");
		Response response =
				
			given()
				.log().all()
				.header("Content-Type","application/json")
				.pathParam("orderId", orderId)
			.when()
				//.get(API_Endpoints.find_order_get_url);// This is fetching data from API_Endpoints.java class
				.get(store_urls.getString("find_order_get_url"));// This is directly fetching the url endpoints from the API_Endpoints.properties file
		return response;
	}
	
	
	//DELETE AN ORDER
	public static Response DeleteOrder(Integer orderId)
	{
		//String delOrder = getURL().getString("delete_order_get_url");
		Response response =
				
				given()
					.log().all()
					.header("Content-Type","application/json")
					.pathParam("orderId", orderId)
				.when()
					//.delete(API_Endpoints.delete_order_get_url);// This is fetching data from API_Endpoints.java class
					.delete(store_urls.getString("delete_order_get_url"));// This is directly fetching the url endpoints from the API_Endpoints.properties file
			return response;
	}

}
