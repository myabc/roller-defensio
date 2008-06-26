package com.defensio;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.defensio.exceptions.DefensioException;
import com.defensio.requests.AnnouneArticleRequest;
import com.defensio.requests.AuditCommentRequest;
import com.defensio.requests.GetStatsRequest;
import com.defensio.requests.ReportFalseNegativesRequest;
import com.defensio.requests.ReportFalsePositivesRequest;
import com.defensio.requests.ValidateKeyRequest;
import com.defensio.responses.AuditCommentResponse;
import com.defensio.responses.DefensioResponse;
import com.defensio.responses.GetStatsResponse;

public class _DefensioWrapperTest extends TestCase {

	private String API_KEY = "a9e3c1722919";
	private String SITE_URL = "http://blog.remygiard.com";
	
	private DefensioWrapper _wrapper = null;
	
	public void setUp() throws Exception {
		_wrapper = new DefensioWrapper();
	}
	
	public void testMissingParameter() throws Exception {
		AuditCommentRequest request = new AuditCommentRequest();
		request.setApiKey(API_KEY);
		request.setArticleDate("2005/12/12");
		request.setCommentAuthor("I love you know what");
		request.setCommentAuthorEmail("viagra@jojo.com");
		request.setCommentAuthorUrl("");
		request.setCommentContent("YA Yoooo viagra viiagra");
		request.setCommentType("comment");
		request.setOwnerUrl(SITE_URL);
		request.setPermalink("");
		request.setReferrer("");
		request.setUserLoggedIn(false);
		request.setUserIP("");
		
		try {
			_wrapper.auditComment(request);
			fail("Should fail for missing parameter.");
		} catch (DefensioException e) {
			assertTrue(e.getMessage().startsWith("Required parameter missing"));
		}
	}
	
	public void testValidateKey() throws Exception {
		ValidateKeyRequest request = new ValidateKeyRequest();
		request.setApiKey(API_KEY);
		request.setOwnerUrl(SITE_URL);
		
		DefensioResponse response = _wrapper.validateKey(request);
		assertNotNull("Should have a response.", response);
		assertEquals("1.1", response.getApiVersion());
		assertEquals("", response.getMessage());
		assertEquals("success", response.getStatus());
		assertTrue(response.isSuccess());
	}
	
	public void testAuditComment() throws Exception {
		AuditCommentRequest request = new AuditCommentRequest();
		request.setApiKey(API_KEY);
		request.setArticleDate("2005/12/12");
		request.setCommentAuthor("I love you know what");
		request.setCommentAuthorEmail("viagra@jojo.com");
		request.setCommentAuthorUrl("");
		request.setCommentContent("YA Yoooo viagra viiagra");
		request.setCommentType("comment");
		request.setOwnerUrl(SITE_URL);
		request.setPermalink("");
		request.setReferrer("");
		request.setUserLoggedIn(false);
		request.setUserIP("10.10.10.10");
		
		AuditCommentResponse response = _wrapper.auditComment(request);
		assertNotNull("Should have a response.", response);
		assertEquals("1.1", response.getApiVersion());
		assertEquals("", response.getMessage());
		assertEquals("success", response.getStatus());
		assertTrue(response.isSuccess());
		assertTrue(response.isSpam());
	}
	
	public void testAnnouneArticle() throws Exception {
		AnnouneArticleRequest request = new AnnouneArticleRequest();
		request.setApiKey(API_KEY);
		request.setArticleAuthor("Remy");
		request.setArticleAuthorEmail("bob@blabal.com");
		request.setArticleContent("this is the content");
		request.setArticleTitle("this is the title");
		request.setOwnerUrl("http://blog.remygiard.com");
		request.setPermalink("http://blog.remygiard.com/testpost");
		
		try {
			DefensioResponse response = _wrapper.announceArticle(request);
			assertNotNull("Should have a response.", response);
			assertEquals("1.1", response.getApiVersion());
			assertEquals("", response.getMessage());
			assertEquals("success", response.getStatus());
			assertTrue(response.isSuccess());
			
		} catch (DefensioException e) {
			assertEquals("Article already exists", e.getMessage());
		}
	}
	
	public void testReportFalsePositivesAndNegatives() throws Exception {		
		AuditCommentRequest commentRequest = new AuditCommentRequest();
		commentRequest.setApiKey(API_KEY);
		commentRequest.setArticleDate("2005/12/12");
		commentRequest.setCommentAuthor("I love you know what");
		commentRequest.setCommentAuthorEmail("viagra@jojo.com");
		commentRequest.setCommentAuthorUrl("");
		commentRequest.setCommentContent("YA Yoooo viagra viiagra");
		commentRequest.setCommentType("comment");
		commentRequest.setOwnerUrl(SITE_URL);
		commentRequest.setPermalink("");
		commentRequest.setReferrer("");
		commentRequest.setUserLoggedIn(false);
		commentRequest.setUserIP("10.10.10.10");
		
		AuditCommentResponse commentResponse = _wrapper.auditComment(commentRequest);
		assertNotNull("Should have a response.", commentResponse);
		assertEquals("1.1", commentResponse.getApiVersion());
		assertEquals("", commentResponse.getMessage());
		assertEquals("success", commentResponse.getStatus());
		assertTrue(commentResponse.isSuccess());
		assertTrue(commentResponse.isSpam());
				
		ReportFalsePositivesRequest positiveRequest = new ReportFalsePositivesRequest();
		ArrayList<String> signatures = new ArrayList<String>();
		signatures.add(commentResponse.getSignature());
		positiveRequest.setSignatures(signatures);
		positiveRequest.setOwnerUrl("http://blog.remygiard.com");
		positiveRequest.setApiKey(API_KEY);
		
		DefensioResponse positiveResponse = _wrapper.reportFalsePositives(positiveRequest);
		assertNotNull("Should have a response.", positiveResponse);
		assertEquals("1.1", positiveResponse.getApiVersion());
		assertEquals("", positiveResponse.getMessage());
		assertEquals("success", positiveResponse.getStatus());
		assertTrue(positiveResponse.isSuccess());
				
		ReportFalseNegativesRequest negativeRequest = new ReportFalseNegativesRequest();
		signatures = new ArrayList<String>();
		signatures.add(commentResponse.getSignature());
		negativeRequest.setSignatures(signatures);
		negativeRequest.setOwnerUrl("http://blog.remygiard.com");
		negativeRequest.setApiKey(API_KEY);
		
		DefensioResponse negativeResponse = _wrapper.reportFalseNegatives(negativeRequest);
		assertNotNull("Should have a response.", negativeResponse);
		assertEquals("1.1", negativeResponse.getApiVersion());
		assertEquals("", negativeResponse.getMessage());
		assertEquals("success", negativeResponse.getStatus());
		assertTrue(negativeResponse.isSuccess());	
	}
	
	public void testGetStats() throws DefensioException {
		GetStatsRequest request = new GetStatsRequest();
		request.setApiKey(API_KEY);
		request.setOwnerUrl("http://blog.remygiard.com");
		
		GetStatsResponse response = _wrapper.getStats(request);
		assertNotNull("Should have a response.", response);
		assertEquals("1.1", response.getApiVersion());
		assertEquals("", response.getMessage());
		assertEquals("success", response.getStatus());
		assertTrue(response.isSuccess());
	}
}
