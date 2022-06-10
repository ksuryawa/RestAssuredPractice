package com.tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.*;

public class PostRequestTest {

	@Test
	public void postUsingString(){
		//Using simple string
		//
	  String reqBody="{\n" +
			  "        \"id\": \"158\",\n" +
			  "        \"firstName\": \"Tom\",\n" +
			  "        \"lastName\": \"Cruise\",\n" +
			  "        \"email\": \"user1@test.co.in\"\n" +
			  "    }";

		String updatedReqBody = reqBody.replace("158", String.valueOf(new Faker().number().numberBetween(100, 900)));
		Response response = given()
				.contentType(ContentType.JSON)
				.body(updatedReqBody)
				.log()
				.all()
				.post("http://localhost:3000/employees");

		response.prettyPrint();
		System.out.println(response.statusCode());
	}

	@Test
	public void postUsingFile(){

		//Pass it from external File

		Response response = given()
				.contentType(ContentType.JSON)
				.body(new File(System.getProperty("user.dir") + "/data.json"))
				.log()
				.all()
				.post("http://localhost:3000/employees");

		response.prettyPrint();
		System.out.println(response.statusCode());
	}

	@Test
	public void postUsingFileAsString() throws IOException {

		//Store FIle data as string

		byte[] bytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/data.json"));

		String reqBody=new String(bytes);
			reqBody=reqBody.replace("678",String.valueOf(new Faker().number().numberBetween(100,500)))
					.replace("Tom",new Faker().name().firstName());

		Response response = given()
				.contentType(ContentType.JSON)
				.body(reqBody)
				.log()
				.all()
				.post("http://localhost:3000/employees");

		response.prettyPrint();
		System.out.println(response.statusCode());

	}

	@Test
	public void postUsingCollections(){

		// {} - > MAP
		// [] -> List

		Map<String,Object> reqBody=new LinkedHashMap<>();

		reqBody.put("id",new Faker().number().numberBetween(100,999));
		reqBody.put("firstName",new Faker().name().firstName());
		reqBody.put("lastName",new Faker().name().firstName());
		reqBody.put("email",new Faker().internet().emailAddress());

		List<String> job=new ArrayList<>();
		job.add("tester");
		job.add("son");

		reqBody.put("jobs",job);

		Map<String,Object> favFood=new LinkedHashMap<>();
		favFood.put("breakFast" , "Pohe");

		List<String> lunch=new ArrayList<>();
		lunch.add("Chapatis");
		lunch.add("Curry");

		List<String> dinner=new ArrayList<>();
		dinner.add("Rice");
		dinner.add("Milk");

		reqBody.put("favFoods" ,favFood);
		reqBody.put("lunch",lunch);
		reqBody.put("dinner",dinner);


		Response response = given().contentType(ContentType.JSON)
				.body(reqBody)
				.log()
				.all()
				.post("http://localhost:3000/employees");

		response.prettyPrint();

		response.then().statusCode(HttpStatus.SC_CREATED);
		System.out.println(response.statusCode());

	}

	@Test
	public void postUsingJson(){
		//{} --> JsonObject
		//[] --> JsonArray

		JSONObject reqBody=new JSONObject();
		reqBody.put("id",new Faker().number().numberBetween(100,999));
		reqBody.put("firstName",new Faker().name().firstName());
		reqBody.put("lastName",new Faker().name().firstName());
		reqBody.put("email",new Faker().internet().emailAddress());
		reqBody.accumulate("email","text@test.in");

		JSONArray job=new JSONArray();
		job.put("tester");
		job.put("son");

		reqBody.put("jobs",job);

		JSONObject favFood=new JSONObject();
		favFood.put("breakFast" , "Pohe");

		JSONArray lunch=new JSONArray();
		lunch.put("Chapatis");
		lunch.put("Curry");

		JSONArray dinner=new JSONArray();
		dinner.put("Rice");
		dinner.put("Milk");

		reqBody.put("favFoods" ,favFood);
		reqBody.put("lunch",lunch);
		reqBody.put("dinner",dinner);


		Response response = given()
				.contentType(ContentType.JSON)
				.body(reqBody.toString())
				.log()
				.all()
				.post("http://localhost:3000/employees");

		response.prettyPrint();

		response.then().statusCode(HttpStatus.SC_CREATED);
		System.out.println(response.statusCode());

	}
}