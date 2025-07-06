package api.test;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPointsProp;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import static org.awaitility.Awaitility.await;

public class DataDrivenTest {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testCreateAndDeleteUser(String userID, String userName, String fname, String lname, String useremail,
			String pwd, String ph) {
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);

		// Create user
		Response createResp = UserEndPointsProp.createUser(userPayload);
		createResp.then().log().all();
		Assert.assertEquals(createResp.getStatusCode(), 200, "User creation failed");

		System.out.println("Waiting for user to be ready: " + userName);
		boolean isAvailable = waitForUserToBeAvailable(userName,5,3);
		Assert.assertTrue(isAvailable, "User not found after creation");

		Response getResp = UserEndPointsProp.readUser(userName);
		String actualUsername = getResp.jsonPath().getString("username");

		// Delete user
		System.out.println("Deleting user: " + actualUsername);
		Response deleteResp = UserEndPointsProp.deleteUser(actualUsername);
		deleteResp.then().log().all();
		Assert.assertEquals(deleteResp.getStatusCode(), 200, "User deletion failed");
	}
	
	public boolean waitForUserToBeAvailable(String username, int maxRetries, int delaySeconds) {
	    for (int i = 0; i < maxRetries; i++) {
	        Response getResponse = UserEndPointsProp.readUser(username);
	        int statusCode = getResponse.getStatusCode();
	        System.out.println("Polling [" + username + "]: " + statusCode);

	        if (statusCode == 200) {
	            return true;
	        }

	        try {
	            TimeUnit.SECONDS.sleep(delaySeconds);  // instead of Thread.sleep
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();  // best practice to reset the interrupt flag
	            return false;
	        }
	    }
	    return false;
	}

}
