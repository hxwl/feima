package com.hx.hxfeima.core.model;

public class JsonMessage {
	
	
	private   boolean success;
	
	private  String  message;
	
	
	private  Integer status;
	
	private  String  data;
	
	private  Object obj;
	
	
	public JsonMessage(){
		
		
		
	}
	
	public JsonMessage(boolean success, Integer status,
			Object obj) {
	
		this.success = success;
		this.status = status;
		this.obj = obj;
	}
	
	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public JsonMessage(boolean success, String message, Integer status,
			String data) {
		super();
		this.success = success;
		this.message = message;
		this.status = status;
		this.data = data;
	}


	public JsonMessage(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}


	public JsonMessage(boolean success, String message, Integer status) {
		super();
		this.success = success;
		this.message = message;
		this.status = status;
	}



	


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

	
	
	
	

}
