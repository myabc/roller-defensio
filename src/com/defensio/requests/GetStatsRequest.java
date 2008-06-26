package com.defensio.requests;

public class GetStatsRequest extends DefensioRequest {

	private String _ownerUrl = null;
	
	public GetStatsRequest() {
		setAction("get-stats");
	}

	public String getOwnerUrl() {
		return _ownerUrl;
	}

	public void setOwnerUrl(String ownerUrl) {
		_ownerUrl = ownerUrl;
	}
	
	
}
