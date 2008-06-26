package com.defensio.requests;

import java.util.List;

public class ReportFalsePositivesRequest extends DefensioRequest {

	private String _ownerUrl = null;
	private List _signatures = null;
	
	public ReportFalsePositivesRequest() {
		setAction("report-false-positives");
	}
	
	public String getOwnerUrl() {
		return _ownerUrl;
	}
	public void setOwnerUrl(String ownerUrl) {
		_ownerUrl = ownerUrl;
	}
	public List getSignatures() {
		return _signatures;
	}
	public void setSignatures(List signatures) {
		_signatures = signatures;
	}
}
