package com.defensio.requests;

public abstract class DefensioRequest {

	private String _apiUrl = "http://api.defensio.com";
	private String _serviceType = "blog";
	private String _apiVersion = "1.1";
	private String _action = null;
	private String _apiKey = null;
	private String _format = "xml";
	
	public String getFullURL() {
		return _apiUrl + "/" + _serviceType + "/" + _apiVersion + "/" 
			+ _action + "/" + _apiKey + "." + _format; 
	}
	
	public String getApiUrl() {
		return _apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		_apiUrl = apiUrl;
	}
	public String getAction() {
		return _action;
	}
	public void setAction(String action) {
		_action = action;
	}
	public String getApiKey() {
		return _apiKey;
	}
	public void setApiKey(String apiKey) {
		_apiKey = apiKey;
	}
	public String getApiVersion() {
		return _apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		_apiVersion = apiVersion;
	}
	public String getServiceType() {
		return _serviceType;
	}
	public void setServiceType(String serviceType) {
		_serviceType = serviceType;
	}
	public String getFormat() {
		return _format;
	}
	public void setFormat(String format) {
		_format = format;
	}
}
