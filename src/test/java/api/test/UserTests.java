package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.User;
import io.restassured.response.Response;


public class UserTests {
		
		Faker faker;
		User userpayload;
		public Logger logger;
	
		@BeforeClass
		public void setUp() 
		{
			faker = new Faker();
			userpayload = new User();
			userpayload.setId(faker.idNumber().hashCode());
			userpayload.setUsername(faker.name().username());
			userpayload.setFirstName(faker.name().firstName());
			userpayload.setLastName(faker.name().lastName());
			userpayload.setEmail(faker.internet().safeEmailAddress());
			userpayload.setPassword(faker.internet().password());
			userpayload.setPhone(faker.phoneNumber().cellPhone());
			
			//logs
			logger = LogManager.getLogger(this.getClass());
		}
		
		@Test(priority =1)
		public void testPostUser() 
		{	
			logger.info("*********creating user*******");
			Response response =userEndPoints.createUser(userpayload);
			response.then().log().all();
			
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("*********user created*******");
		}
		
		@Test(priority =2)
		public void testGetUserByName()
		{
			logger.info("*********Reading user info*******");
			Response response = userEndPoints.readUser(this.userpayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("*********user info displayed*******");
		}
		
		@Test(priority =3)
		public void testUpdateUserByName()
		{	
			logger.info("*********user info updating*******");
			userpayload.setFirstName(faker.name().firstName());
			userpayload.setLastName(faker.name().lastName());
			userpayload.setEmail(faker.internet().safeEmailAddress());
			
			Response response = userEndPoints.updateUser(userpayload ,this.userpayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			
			//checking data after the update
			Response responseafterdata = userEndPoints.readUser(this.userpayload.getUsername());
			responseafterdata.then().log().all();
			Assert.assertEquals(responseafterdata.getStatusCode(), 200);
			logger.info("*********user updated info*******");
		}
		
		@Test(priority =4)
		public void testDeleteUserByName()
		{	
			logger.info("*********Deleteing the user info*******");
			Response response = userEndPoints.deleteUser(this.userpayload.getUsername());
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("*********user info deleted*******");
			
			
		}
}
