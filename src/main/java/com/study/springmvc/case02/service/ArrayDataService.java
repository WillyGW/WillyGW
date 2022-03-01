package com.study.springmvc.case02.service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ArrayDataService {
	
	public List<String> getNames(){
		return Arrays.asList("John", "Mary", "Hellen");
	}
	
	public Map<String, Integer> getFruit(){
		Map<String, Integer> fruits = new LinkedHashMap<String, Integer>();
		fruits.put("apple", 50);
		fruits.put("orange", 30);
		fruits.put("watermelon", 100);
		fruits.put("banana", 15);
		return fruits;		
	}
}
