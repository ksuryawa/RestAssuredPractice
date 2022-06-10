package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FavFood {

	private String breakFast;
	private List<String> lunch;
	private List<String> dinner;

}