package api.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreOperations;
import api.payloads.StorePOJO;
import io.restassured.response.Response;

public class Store_test {
	
	//Create Store Test Data
	StorePOJO store_payload = new StorePOJO();
	Faker faker = new Faker();
	
	@BeforeClass
	public void CreateStoreData()
	{
		store_payload.setId(faker.number().numberBetween(1, 10));
		store_payload.setPetId(faker.number().numberBetween(1, 10));
		store_payload.setQuantity(faker.number().numberBetween(1, 10));
		store_payload.setStatus(null);
		
	}
	
	
	//CREATE ORDER
	@Test(priority=1)
	public void Test_CreateOrder()
	{
		
		System.out.println("**********CREATE STORE ORDER*************");
		Response response = StoreOperations.CreateOrder(store_payload);
		
		response
		.then().log().all()
		.assertThat().statusCode(200);
	}
	
	
	//GET ORDER
	@Test(priority=2)
	public void Test_GetOrder()
	{
		System.out.println("**********GET ORDER*************");
		Response response = StoreOperations.GetOrder(store_payload.getId());
		response
		.then().log().all()
		.assertThat().statusCode(200);
	}

	
	
	
	//DELETE ORDER
	@Test(priority=3)
	public void Test_DeleteOrder()
	{
		System.out.println("**********DELETE ORDER*************");
		Response response = StoreOperations.DeleteOrder(store_payload.getId());
		response
		.then().log().all()
		.assertThat().statusCode(200);
	}
	
	
	

}
