package com.tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
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

		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);

	}
}