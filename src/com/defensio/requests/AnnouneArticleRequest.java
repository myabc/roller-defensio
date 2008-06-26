package com.defensio.requests;

public class AnnouneArticleRequest extends DefensioRequest {

	private String _ownerUrl = null;
	private String _articleAuthor = null;
	private String _articleAuthorEmail = null;
	private String _articleTitle = null;
	private String _articleContent = null;
	private String _permalink = null;
	
	public AnnouneArticleRequest() {
		setAction("announce-article");
	}	
	public String getArticleAuthor() {
		return _articleAuthor;
	}
	public void setArticleAuthor(String articleAuthor) {
		_articleAuthor = articleAuthor;
	}
	public String getArticleAuthorEmail() {
		return _articleAuthorEmail;
	}
	public void setArticleAuthorEmail(String articleAuthorEmail) {
		_articleAuthorEmail = articleAuthorEmail;
	}
	public String getArticleContent() {
		return _articleContent;
	}
	public void setArticleContent(String articleContent) {
		_articleContent = articleContent;
	}
	public String getArticleTitle() {
		return _articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		_articleTitle = articleTitle;
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
}
