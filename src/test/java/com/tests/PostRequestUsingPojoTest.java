package com.tests;

import com.github.javafaker.Faker;
import com.pojo.Employee;
import com.pojo.FavFoods;
import com.pojo.Marks;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PostRequestUsingPojoTest {

	//{} --> Class
	//[] --> List<T>

	@Test
	public void createEmployeeUsingPojo() {

		Faker faker = new Faker();

		FavFoods favFoods =
				new FavFoods("Upma", Arrays.asList("Curry", "Bhakri"), Arrays.asList("Oats", "milk"));

		Marks sem1 = new Marks(faker.number().numberBetween(70, 99), faker.number().numberBetween(70, 99));
		Marks sem2 = new Marks(faker.number().numberBetween(70, 99), faker.number().numberBetween(70, 99));

		Employee employee =
				new Employee(new Faker().number().numberBetween(300, 999), faker.name().firstName(),
						faker.name().lastName(), faker.internet().emailAddress(), favFoods, Arrays.asList(sem1, sem2), Arrays.asList("Student", "freelancer"));

		Response response = given().contentType(ContentType.JSON)
				.log()
				.all()
				.body(employee)
				.post("http://localhost:3000/employees");

		response.prettyPrint();

		response.then().statusCode(HttpStatus.SC_CREATED);
		System.out.println(response.statusCode());

		System.out.println(response.jsonPath().getString("firstName"));
		String string = response.jsonPath().getString("favFoods.lunch[0]");
		System.out.println(string);

		Employee deserializeEmployee = response.as(Employee.class);
		System.out.println(deserializeEmployee.getEmail());

		response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("employeeSchema.json")); //using classpath

		response.then().body(JsonSchemaValidator.matchesJsonSchema(new File(System.getProperty("user.dir") + "/src/test/resources/jsonschemas/schema.json")));

		assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
		assertThat(response.jsonPath().getString("firstName")).isEqualTo(employee.getFirstName());
		assertThat(response.jsonPath().getString("lastName")).isEqualTo(employee.getLastName());
		assertThat(response.jsonPath().getString("email")).isEqualTo(employee.getEmail());


	}
}