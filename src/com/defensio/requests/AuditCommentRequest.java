package com.defensio.requests;

public class AuditCommentRequest extends DefensioRequest {

	private String _ownerUrl = null;
	private String _userIP = null;
	private String _articleDate = null;
	private String _commentAuthor = null;
	private String _commentType = null;
	private String _commentContent = null;
	private String _commentAuthorEmail = null;
	private String _commentAuthorUrl = null;
	private String _permalink =  null;
	private String _referrer = null;
	private boolean _userLoggedIn = false;
	
	public AuditCommentRequest() {
		setAction("audit-comment");
	}
	
	public String getArticleDate() {
		return _articleDate;
	}
	public void setArticleDate(String articleDate) {
		_articleDate = articleDate;
	}
	public String getCommentAuthor() {
		return _commentAuthor;
	}
	public void setCommentAuthor(String commentAuthor) {
		_commentAuthor = commentAuthor;
	}
	public String getCommentAuthorEmail() {
		return _commentAuthorEmail;
	}
	public void setCommentAuthorEmail(String commentAuthorEmail) {
		_commentAuthorEmail = commentAuthorEmail;
	}
	public String getCommentAuthorUrl() {
		return _commentAuthorUrl;
	}
	public void setCommentAuthorUrl(String commentAuthorUrl) {
		_commentAuthorUrl = commentAuthorUrl;
	}
	public String getCommentContent() {
		return _commentContent;
	}
	public void setCommentContent(String commentContent) {
		_commentContent = commentContent;
	}
	public String getCommentType() {
		return _commentType;
	}
	public void setCommentType(String commentType) {
		_commentType = commentType;
	}
	public String getOwnerUrl() {
		return _ownerUrl;
	}
	public void setOwnerUrl(String ownerUrl) {
		_ownerUrl = ownerUrl;
	}
	public String getPermalink() {
		return _permalink;
	}
	public void setPermalink(String permalink) {
		_permalink = permalink;
	}
	public String getReferrer() {
		return _referrer;
	}
	public void setReferrer(String referrer) {
		_referrer = referrer;
	}
	public String getUserIP() {
		return _userIP;
	}
	public void setUserIP(String userIP) {
		_userIP = userIP;
	}
	public boolean isUserLoggedIn() {
		return _userLoggedIn;
	}
	public void setUserLoggedIn(boolean userLoggedIn) {
		_userLoggedIn = userLoggedIn;
	}
}
