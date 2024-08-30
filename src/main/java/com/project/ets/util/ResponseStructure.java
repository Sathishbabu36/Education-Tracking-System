package com.project.ets.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	
	private int status;
	private String message;
	private T data;
	
	public static <T> ResponseStructure<T> create(int status,String message,T data){
		ResponseStructure<T> structure = new ResponseStructure<T>();
		structure.setStatus(status);
		structure.setMessage(message);
		structure.setData(data);
		
		return structure;
	}
	
}
