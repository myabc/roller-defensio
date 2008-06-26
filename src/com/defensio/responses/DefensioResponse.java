package com.defensio.responses;

public class DefensioResponse {

	private String _status = null;
	private String _message = null;
	private String _apiVersion = null;
	
	public String getApiVersion() {
		return _apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		_apiVersion = apiVersion;
	}
	public String getMessage() {
		return _message;
	}
	public void setMessage(String message) {
		_message = message;
	}
	public String getStatus() {
		return _status;
	}
	public void setStatus(String status) {
		_status = status;
	}
	
	public boolean isSuccess() {
		return "success".equals(_status);
	}
}
