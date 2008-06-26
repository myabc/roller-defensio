package com.defensio.requests;

import java.util.List;

public class ReportFalseNegativesRequest extends DefensioRequest {

	private String _ownerUrl = null;
	private List _signatures = null;
	
	public ReportFalseNegativesRequest() {
		setAction("report-false-negatives");
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
