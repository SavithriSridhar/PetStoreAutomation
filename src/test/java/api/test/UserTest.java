package api.test;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsProp;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userPayload;
	public Logger logger;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
//		userPayload.setId(101);
//		userPayload.setUsername("testuser123");
//		userPayload.setFirstname("John");
//		userPayload.setLastname("Doe");
//		userPayload.setEmail("john.doe@example.com");
//		userPayload.setPassword("test123");
//		userPayload.setPhone("1234567890");
		
		logger = LogManager.getLogger(this.getClass()); 
	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.info("**************** Creating User *******************");
		Response response = UserEndPointsProp.createUser(userPayload);
		System.out.println(response.then().log().all().toString());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User is created *******************");
	}

	@Test(priority = 2)
	public void testGetUserByName() throws InterruptedException  {
		logger.info("**************** Reading User Info *******************");
		Thread.sleep(5000);
		Response response = UserEndPointsProp.readUser(this.userPayload.getUsername());
		response.then().log().all().toString();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User info is displayed *******************");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.info("**************** Updating User *******************");
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndPointsProp.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterUpdate = UserEndPointsProp.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("**************** User Updated *******************");
	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("**************** Deleting User *******************");
		Response response = UserEndPointsProp.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User Deleted *******************");
	}
}
