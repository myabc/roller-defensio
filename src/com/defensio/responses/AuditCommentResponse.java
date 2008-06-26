package com.defensio.responses;

public class AuditCommentResponse extends DefensioResponse {

	private String _signature = null;
	private boolean _spam = false;
	private float _spaminess = 0;
	
	public String getSignature() {
		return _signature;
	}
	public void setSignature(String signature) {
		_signature = signature;
	}
	public boolean isSpam() {
		return _spam;
	}
	public void setSpam(boolean spam) {
		_spam = spam;
	}
	public float getSpaminess() {
		return _spaminess;
	}
	public void setSpaminess(float spaminess) {
		_spaminess = spaminess;
	}
}
