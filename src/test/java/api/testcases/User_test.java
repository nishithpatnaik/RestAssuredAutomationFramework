package api.testcases;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserOperations;
import api.payloads.UserPOJO_payload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class User_test {
	
	Faker faker = new Faker();
	UserPOJO_payload user_payload = new UserPOJO_payload();

	@BeforeClass
	public void Generate_UserData()
	{	
		user_payload.setId(faker.idNumber().hashCode());
		user_payload.setUserName(faker.name().username());
		user_payload.setFirstName(faker.name().firstName());
		user_payload.setLastName(faker.name().lastName());
		user_payload.setEmail(faker.internet().emailAddress());
		user_payload.setPassword(faker.internet().password(5,10));
		user_payload.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void Test_CreateUser()
	{
		Response response = UserOperations.CreateUser(user_payload);
		
		System.out.println("CREATE USER -- ");
		response.then()
		.log().all()
		.assertThat().statusCode(200);
	}
	
	@Test(priority=2)
	public void Test_GetUser()
	{
		System.out.println("FETCH AN USER -- ");
		String userName = this.user_payload.getUserName();
		Response response = UserOperations.GetUser(userName);
		response.then()
		.log().all();
		
		//Validate Status Code
		Assert.assertEquals(response.getStatusCode(), 404);
	}
	
	
	
	@Test(priority=3)
	public void Test_UpdateUser()
	{
		System.out.println("UPDATE AN USER -- ");
		
		user_payload.setEmail(faker.internet().emailAddress());
		
		Response response = UserOperations.UpdateUser(user_payload);
		
		response.then()
				.log().all()
				.assertThat()
				.statusCode(equalTo(200));
	}
	
	
	
	@Test(priority=4)
	public void Test_Delete()
	{
		
		System.out.println("DELETE AN USER -- ");
		Response response = UserOperations.DeleteUser(this.user_payload.getFirstName());
		response.then()
		.log().all();
		//.assertThat().statusCode(200);
	}

}
