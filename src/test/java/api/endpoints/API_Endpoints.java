package api.endpoints;

//This Class is supposed to house only the Service Endpoints
public class API_Endpoints {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//USER MODULE ENDPOINTS
	public static String create_user_post__url = base_url+"/user";
	public static String find_user_get_url = base_url+"/user/{username}";
	public static String update_user_put_url = base_url+"/user/{username}";
	public static String user_delete_url = base_url+"/user/{username}";
	
	//STORE MODULE ENDPOINTS
	public static String place_order_post_url = base_url+"/store/order";
	public static String find_order_get_url = base_url+"/store/order/{orderId}";
	public static String delete_order_get_url = base_url+"/store/order/{orderId}";
	
	//PET MODULE ENDPOINTS
	public static String new_pet_post_url = base_url+"/pet";
	public static String update_pet_put_url = base_url+"pet";
	public static String find_pet_get_url = base_url+"pet/{pedId}";
	public static String delete_pet_delete_url = base_url+"pet/{pedId}";

}
