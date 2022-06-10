package com.tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequestTest {

	@Test
	public void deleteEmployee() {

		Response response = given()
				.pathParam("id", 5)
				.log()
				.all()
				.delete("http://localhost:3000/employees/{id}");

		System.out.println(response.statusCode());
		
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);

	}
}