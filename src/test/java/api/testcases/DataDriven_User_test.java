package api.testcases;

import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.UserOperations;
import api.payloads.UserPOJO_payload;
import api.utilities.User_DataProviders;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DataDriven_User_test {
	
	
	@Test(priority=1, dataProvider="UsersDetails_data", dataProviderClass = User_DataProviders.class)
	public void Test_CreateUser(String Id, String UserName, String FirstName, String LastName, String Email, String Password, String Phone)
	{
		System.out.println("**********CREATE USER*************");
		UserPOJO_payload user_payload = new UserPOJO_payload();
		user_payload.setId(Integer.parseInt(Id));
		user_payload.setUserName(UserName);
		user_payload.setFirstName(FirstName);
		user_payload.setLastName(LastName);
		user_payload.setEmail(Email);
		user_payload.setPassword(Password);
		user_payload.setPhone(Phone);
		
		Response response = UserOperations.CreateUser(user_payload);
		
		System.out.println("CREATE USER -- ");
		response.then()
			.log().all()
			.assertThat().statusCode(200);
	}
	
	@Test(priority=2, dataProvider="UserNames_Data",dataProviderClass = User_DataProviders.class)
	public void Test_GetUser(String userName)
	{
		System.out.println("FETCH AN USER -- ");
		Response response = UserOperations.GetUser(userName);
		response.then()
		.log().all();
		
		//Validate Status Code
		Assert.assertEquals(response.getStatusCode(), 404);
	}
	
	
	
	//@Test(priority=3, dataProvider="UserNames_Data",dataProviderClass = User_DataProviders.class)
	public void Test_UpdateUser(String userName)
	{
		System.out.println("UPDATE AN USER -- ");
		
		Response response = UserOperations.UpdateUser(userName);
		
		String res = response.then()
		.log().all().extract().asString();
		JsonPath jp1 = new JsonPath(res);
		Assert.assertEquals(jp1.get("message"), "no data");
	}
	
	
	
	@Test(priority=4, dataProvider="UserNames_Data",dataProviderClass = User_DataProviders.class)
	public void Test_Delete(String userName)
	{
		
		System.out.println("DELETE AN USER -- ");
		Response response = UserOperations.DeleteUser(userName);
		response.then()
		.log().all();
		//.assertThat().statusCode(200);
	}

}
