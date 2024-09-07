package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID ,String username, String firstName, String lastName, String email, String password,String phone) 
	{	
		User userpayload = new User();
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(username);
		userpayload.setFirstName(firstName);
		userpayload.setLastName(lastName);
		userpayload.setEmail(email);
		userpayload.setPassword(password);
		userpayload.setPhone(phone);
		
		Response response = userEndPoints.createUser(userpayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserName", dataProviderClass = DataProviders.class)
	public void testGetUserByName(String userName) 
	{	
		
		Response response = userEndPoints.readUser(userName);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3, dataProvider = "UserName", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) 
	{	
		
		Response response = userEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
