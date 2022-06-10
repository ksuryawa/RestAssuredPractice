package com.tests;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequestTest {
	@Test
	public void getEmployees() {
		Response response = given().get("http://localhost:3000/employees");

		System.out.println(response.statusCode());
		System.out.println(response.time());
		System.out.println(response.contentType());

		response.then().statusCode(HttpStatus.SC_OK);

		for (Header header : response.headers()) {
			System.out.println(header.getName() + " :" + header.getValue());
		}

		response.prettyPrint();
	}

	@Test
	public void getEmployee() {
		Response response = given()
				.log()
				.all()
				.queryParam("id", 3)
				.get("http://localhost:3000/employees");

		response.prettyPrint();
		System.out.println(response.statusCode());

	}
}