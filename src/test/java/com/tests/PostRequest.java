package com.tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;

import static io.restassured.RestAssured.*;

public class PostRequest {

	@Test
	public void postUsingString(){
		//Using simple string
		//

	  String reqbody="{\n" +
			  "        \"id\": \"158\",\n" +
			  "        \"firstName\": \"Tom\",\n" +
			  "        \"lastName\": \"Cruise\",\n" +
			  "        \"email\": \"user1@test.co.in\"\n" +
			  "    }";

		Response response = given()
				.contentType(ContentType.JSON)
				.body(reqbody)
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
}