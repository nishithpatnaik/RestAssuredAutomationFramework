package api.testcases;

import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.PetOperations;
import api.payloads.Category;
import api.payloads.PetPOJO;
import api.payloads.Tags;
import api.utilities.Pet_DataProvider;
import io.restassured.response.Response;

public class DataDriven_Pet_test {
	
	PetPOJO pet_payload = new PetPOJO();
	@Test(priority=1, dataProvider = "PetDetailsData", dataProviderClass = Pet_DataProvider.class)
	public void Test_AddPet(Double petId, Double categoryId, String categoryName, String petName, Double tagId, String tagName, String status)
	{
		
		System.out.println("**********ADD PET************");
		Faker faker = new Faker();
		
		
		List<String>photoURL = new ArrayList<>();
		photoURL.add(faker.internet().url().toString());
		
		Category category = new Category();
		category.setId(categoryId.intValue());
		category.setName(categoryName);
		
		Tags tag = new Tags();
		tag.setId(tagId.intValue());
		tag.setName(tagName);
		List<Tags> newTag = new ArrayList<>();
		newTag.add(tag);
		
		pet_payload.setId(petId.intValue());
		pet_payload.setName(petName);
		pet_payload.setPhotoUrls(photoURL);
		pet_payload.setCategory(category);
		pet_payload.setTags(newTag);
		pet_payload.setStatus(status);
		
		Response response = PetOperations.AddPet(pet_payload);
		
		response.
			then()
				.log().all()
				.assertThat()
				.statusCode(equalTo(200))
				.body("id", equalTo(pet_payload.getId()))
				.body("category.name", equalTo(pet_payload.getCategory().getName()))
				.body("tags[0].id", equalTo(pet_payload.getTags().get(0).getId()));
				
		
	}
	
	@Test(priority=2)
	public void Test_FindPet()
	{
		System.out.println("**********FIND PET************");
		Response response = PetOperations.FindPet(pet_payload.getId());
		response.then()
		.log().all()
		.assertThat()
		.statusCode(equalTo(200))
		.body("id", equalTo(pet_payload.getId()))
		.body("name", equalTo(pet_payload.getName()))
		.body("category.name", equalTo(pet_payload.getCategory().getName()));
	}
	
	@Test(priority=3)
	public void Test_DeletePet()
	{
		System.out.println("**********DELETE PET************");
		
		Response response = PetOperations.DeletePet(pet_payload.getId());
		response.then()
		.log().all()
		.assertThat()
		.statusCode(equalTo(200))
		.body("message",equalTo(pet_payload.getId().toString()));
	}
	
	
	
	
	

}
