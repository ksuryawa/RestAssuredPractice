package com.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateRequestTest {

	@Test
	public void updateEmployee() {

		JSONObject object = new JSONObject();
		object.put("firstName", "abv");
		object.put("lastName", "xyz");

		Response response = given().contentType(ContentType.JSON)
				.pathParam("id", 3)
				.log()
				.all()
				.body(object.toMap())
				.put("http://localhost:3000/employees/{id}");

		response.prettyPrint();
		System.out.println(response.statusCode());

		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
	}
}