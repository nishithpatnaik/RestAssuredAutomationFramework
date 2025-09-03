package api.testcases;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import api.endpoints.PetOperations;
import api.payloads.Category;
import api.payloads.PetPOJO;
import api.payloads.Tags;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Pet_test {
	
	PetPOJO pet_payload = new PetPOJO();
	Faker faker = new Faker();
	
	@BeforeClass
	public void CreatePetData() throws JsonProcessingException
	{
		//ID
		pet_payload.setId(faker.number().numberBetween(1, 10));
		
		//Category
		Category category = new Category();
		category.setId(faker.number().numberBetween(1, 10));
		category.setName(faker.animal().name());
		pet_payload.setCategory(category);
		
		//Name
		pet_payload.setName(faker.animal().name());
		
		//Photo Urls List
		List<String> photoUrls = new ArrayList<>();
		photoUrls.add(faker.internet().url());
		pet_payload.setPhotoUrls(photoUrls);
		
		//Tag List
		Tags tag1 = new Tags();
		tag1.setId(faker.number().numberBetween(1, 50));
		tag1.setName(faker.animal().name());
		Tags tag2 = new Tags();
		tag2.setId(faker.number().numberBetween(1, 50));
		tag2.setName(faker.animal().name());
		List<Tags> taglist = new ArrayList<>();
		taglist.add(tag1);
		taglist.add(tag2);
		pet_payload.setTags(taglist);
		pet_payload.setStatus("available");
		
		

		
	}
	
	
	@Test(priority=1)
	public void Test_AddPet()
	{
		System.out.println("**********ADD PET************");
		Response response = PetOperations.AddPet(pet_payload);
		response.then()
					.log().all()
					.assertThat()
					.statusCode(equalTo(200))
					.body("id", equalTo(pet_payload.getId()))
					.body("category.name", equalTo(pet_payload.getCategory().getName()))
					.body("tags[0].id", equalTo(pet_payload.getTags().get(0).getId()));
		
		/*
		 * JsonPath jp = response.jsonPath(); int size = (jp.getList("tags").size());
		 * for(int i=0;i<size;i++) { System.out.println(jp.getInt("tags["+i+"].id"));
		 * System.out.println(jp.getString("tags["+i+"].name"));
		 * 
		 * 
		 * }
		 */
		
		
	}
	
	@Test(priority=2)
	public void Test_FindPet()
	{
		System.out.println("***********FIND PET************");
	}

}
