package com.tests;

import com.github.javafaker.Faker;
import com.pojo.Employee;
import com.pojo.FavFood;
import com.pojo.Marks;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class PostRequestUsingPojoTest {

	//{} --> Class
	//[] --> List<T>

	@Test
	public void createEmployeeUsingPojo() {

		Faker faker = new Faker();

		FavFood favFood =
				new FavFood("Upma", Arrays.asList("Curry", "Bhakri"), Arrays.asList("Oats", "milk"));

		Marks sem1 = new Marks(faker.number().numberBetween(70, 99), faker.number().numberBetween(70, 99));
		Marks sem2 = new Marks(faker.number().numberBetween(70, 99), faker.number().numberBetween(70, 99));

		Employee employee =
				new Employee(new Faker().number().numberBetween(300, 999), faker.name().firstName(),
						faker.name().lastName(), faker.internet().emailAddress(), favFood, Arrays.asList(sem1, sem2));

		Response response = given().contentType(ContentType.JSON)
				.log()
				.all()
				.body(employee)
				.post("http://localhost:3000/employees");

		response.prettyPrint();

		response.then().statusCode(HttpStatus.SC_CREATED);
		System.out.println(response.statusCode());

		Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);

		System.out.println(response.jsonPath().getString("firstName"));
		String string = response.jsonPath().getString("favFood.lunch[0]");
		System.out.println(string);
	}
}