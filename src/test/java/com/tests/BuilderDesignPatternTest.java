package com.tests;

import com.github.javafaker.Faker;
import com.pojo.Student;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BuilderDesignPatternTest {

	@Test
	public void builderTest() {

		Faker faker = new Faker();

		Student student = Student.builder()
				.setId(faker.number().numberBetween(100, 896))
				.setFirstName("Kapil")
				.setLastName("Patil")
				.setEmail(faker.internet().emailAddress())
				.setJobs(Arrays.asList("Student", "Tester"))
				.build();

		Response response = given()
				.contentType(ContentType.JSON)
				.baseUri("http://localhost:3000")
				.log()
				.all()
				.body(student)
				.post("/employees");

		response.prettyPrint();
		System.out.println(response.statusCode());

		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);

	}
}