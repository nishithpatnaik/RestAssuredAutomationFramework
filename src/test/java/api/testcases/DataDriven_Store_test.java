package api.testcases;

import org.testng.annotations.Test;
import api.endpoints.StoreOperations;//Test with API_Endpoints.java
import api.endpoints.StoreOperations2; //Test with API_Endpoints.properties
import api.payloads.StorePOJO;
import api.utilities.Store_DataProvider;
import io.restassured.response.Response;

public class DataDriven_Store_test {
	

	
	//CREATE ORDER
	@Test(priority=1, dataProvider = "StoreDetailsData", dataProviderClass = Store_DataProvider.class)
	public void Test_CreateOrder(Double id, Double petId, Double quantity, String status)
	{
		
		System.out.println("**********CREATE STORE ORDER*************");
		
		//Create Store POJO Object
		StorePOJO store_payload = new StorePOJO();
		
		store_payload.setId(id.intValue()); //NOTE: The excel always treats numeric cells as double when read using Apache POISo 
		store_payload.setPetId(petId.intValue());
		store_payload.setQuantity(quantity.intValue());
		store_payload.setStatus(status);
		
		//Response response = StoreOperations.CreateOrder(store_payload); //Test with API_Endpoints.java
		Response response = StoreOperations2.CreateOrder(store_payload); //Test with API_Endpoints.properties
		
		response
		.then().log().all()
		.assertThat().statusCode(200);
	}
	
	
	//GET ORDER
	@Test(priority=2, dataProvider = "StoreIds", dataProviderClass = Store_DataProvider.class)
	public void Test_GetOrder(Double id)
	{
		System.out.println("**********GET ORDER*************");
		//Response response = StoreOperations.GetOrder(id.intValue());//Test with API_Endpoints.java
		Response response = StoreOperations2.GetOrder(id.intValue());//Test with API_Endpoints.properties
		response
		.then().log().all()
		.assertThat().statusCode(200);
	}

	
	
	
	//DELETE ORDER
	@Test(priority=3, dataProvider = "StoreIds", dataProviderClass = Store_DataProvider.class)
	public void Test_DeleteOrder(Double id)
	{
		System.out.println("**********DELETE ORDER*************");
		//Response response = StoreOperations.DeleteOrder(id.intValue());//Test with API_Endpoints.java
		Response response = StoreOperations2.DeleteOrder(id.intValue()); //Test with API_Endpoints.properties
		response
		.then().log().all()
		.assertThat().statusCode(200);
	}
	
	
	

}
