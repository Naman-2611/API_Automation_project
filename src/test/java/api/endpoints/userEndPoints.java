package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//created for perform create,read,update,delete the user API

public class userEndPoints {
		
		public static Response createUser(User payload) //payload is request body
		{
			Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
			.post(Routes.post_url);
			
			return response;
		}
		
		public static Response readUser(String userName) 
		{
			Response response = given()
				.pathParam("username", userName)
			.when()
			.get(Routes.get_url);
			
			return response;
		}
		
		public static Response updateUser(User payload,String userName) //payload is request body
		{
			Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
			.when()
			.put(Routes.update_url);
			
			return response;
		}
		
		public static Response deleteUser(String userName) 
		{
			Response response = given()
				.pathParam("username", userName)
			.when()
			.delete(Routes.delete_url);
			
			return response;
		}
}
