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
	private String firstname;
	private String lastname;
	private String email;
	private FavFood favFood;
	private List<Marks> marks;

}