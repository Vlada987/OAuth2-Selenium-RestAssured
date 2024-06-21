package tests;

import browserActions.Methods_S;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_Class {

	String client_id = "3wss5qhs3lf7tiu";
	String redirect_url = "https://www.google.com/";
	String client_secret = "asda234asd3sdf34";

	String username = "xxx";
	String password = "xxx";

	String code = "";
	String token = "";

	@Test(priority = 1)
	public void getOAuth2Token() {

		Methods_S mse = new Methods_S();
		code = mse.get_Code(client_id, redirect_url, username, password);

		Response resp = given().contentType(ContentType.URLENC).formParam("code", code)
				.formParam("grant_type", "authorization_code").formParam("redirect_uri", redirect_url)
				.formParam("client_id", client_id).formParam("client_secret", client_secret)
				.post("https://api.dropbox.com/oauth2/token");

		Assert.assertTrue(resp.statusCode() == 200);
		token = resp.jsonPath().get("access_token");

	}

	@Test(priority = 2)
	public void passTheOaut2Token() {

		Response resp = given().auth().oauth2(token).contentType(ContentType.JSON)
				.body("{\"path\": \"\",\"recursive\": false}").post("https://api.dropboxapi.com/2/files/list_folder");

		Assert.assertTrue(resp.statusCode() == 200);
	}

}
