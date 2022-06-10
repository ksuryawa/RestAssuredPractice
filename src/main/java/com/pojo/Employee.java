package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private FavFood favFood;
	private List<Marks> marks;

}