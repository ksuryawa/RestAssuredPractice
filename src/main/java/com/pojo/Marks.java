package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Marks {
	private int math;
	private int english;

	public Marks() {
		// TODO document why this constructor is empty
	}
}