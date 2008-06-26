package com.defensio.requests;

public class ValidateKeyRequest extends DefensioRequest {

	private String _ownerUrl = null;

	public ValidateKeyRequest() {
		setAction("validate-key");
	}
		
	public String getOwnerUrl() {
		return _ownerUrl;
	}

	public void setOwnerUrl(String ownerUrl) {
		_ownerUrl = ownerUrl;
	}
	
}
