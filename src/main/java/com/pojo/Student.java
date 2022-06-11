package com.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Builder(setterPrefix = "set")
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private FavFoods favFoods;
	private List<Marks> marks;
	private List<String> jobs;
}