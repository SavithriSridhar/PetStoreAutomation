package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPointsProp;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTestWithPropFile {

	@Test(priority=1,dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String fname,String lname, String useremail, String pwd,String ph) {
		User userPayload = new User();
//		userPayload.setId(101);
//		userPayload.setUsername("testuser123");
//		userPayload.setFirstname("John");
//		userPayload.setLastname("Doe");
//		userPayload.setEmail("john.doe@example.com");
//		userPayload.setPassword("test123");
//		userPayload.setPhone("1234567890");

		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndPointsProp.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2,dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String username) {
		Response response = UserEndPointsProp.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}
