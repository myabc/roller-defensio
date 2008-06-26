package com.defensio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.defensio.exceptions.AccesDeniedException;
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

/**
 * Wraps the available procedures of the Defensio API.
 * @author Remy Giard
 */
public class DefensioWrapper {
	
	public DefensioWrapper() {
	}
	
	/**
	 * Sends the post request to the API server and returns
	 * the response in string. 
	 * @param post
	 * @return
	 * @throws DefensioException
	 */
	private String sendRequest(PostMethod post) throws DefensioException {
		
		try {
			// send the post request and get response
			HttpClient client = new HttpClient();
			int statusCode = client.executeMethod(post);
			switch (statusCode) {
				case -1:
					throw new DefensioException("No response returned.");
					
				case 401:
					throw new AccesDeniedException();
					
				case 200:
					String contents = post.getResponseBodyAsString();
					post.releaseConnection();
				    return contents;
				    
				default :
					throw new DefensioException("Invalid response : " + statusCode);
			}
			
		} catch (DefensioException e) {
			throw e;
			
		} catch (HttpException e) {
			throw new DefensioException(e);
			
		} catch (IOException e) {
			throw new DefensioException(e);
		}
	}
	
	/**
	 * Returned the parameters contained in the response as a HashMap
	 * where the key is the parameter name and the value is the 
	 * parameter value.
	 * @param response
	 * @return
	 * @throws DefensioException
	 */
	private HashMap parseResponse(String response) throws DefensioException {
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			ByteArrayInputStream in = new ByteArrayInputStream(response.getBytes());
			Document doc = builder.parse(in);
			
			// find the "defensio-result" node
			Node result = doc.getChildNodes().item(0);
			if (!"defensio-result".equals(result.getNodeName())) {
				throw new DefensioException("Invalid response.");
			}
			
			HashMap<String, String> params = new HashMap<String, String>();
			for (int i = 0; i < result.getChildNodes().getLength(); i++) {
				Node node = (Node) result.getChildNodes().item(i);
				
				if (!"#text".equals(node.getNodeName())) {
					params.put(node.getNodeName(), node.getTextContent());
				}
				
				if ("message".equals(node.getNodeName()) 
						&& node.getTextContent().trim().length() > 0) {
					 throw new DefensioException(node.getTextContent());
				}
			}
			
			return params;
			
		} catch (SAXException e) {
			throw new DefensioException("Invalid response.");
			
		} catch (IOException e) {
			throw new DefensioException(e);
			
		} catch (ParserConfigurationException e) {
			throw new DefensioException(e);
		}		
	}
	
	/**
	 * Populates in the response the parameters contained in every Defensio response.
	 * @param response
	 * @param params
	 */
	private void populateDefaultParametersInResponse(DefensioResponse response, HashMap params) {
		response.setMessage((String) params.get("message"));
		response.setStatus((String) params.get("status"));
		response.setApiVersion((String) params.get("api-version"));
	}

	/**
	 * API call to validate-key.
	 * @param request
	 * @return
	 * @throws DefensioException
	 */
	public DefensioResponse validateKey(ValidateKeyRequest request) throws DefensioException {
		
		// prepare the post request
		PostMethod post = new PostMethod(request.getFullURL());
		post.addParameter("owner-url", request.getOwnerUrl());
		
		String postResponse = sendRequest(post);
		HashMap parsedResponse = parseResponse(postResponse);
		
		DefensioResponse response = new DefensioResponse();
		populateDefaultParametersInResponse(response, parsedResponse);
		
		return response;
	}
	
	/**
	 * API call to audit-comment
	 * @param request
	 * @return
	 * @throws DefensioException
	 */
	public AuditCommentResponse auditComment(AuditCommentRequest request) throws DefensioException {
		PostMethod post = new PostMethod(request.getFullURL());
		post.addParameter("owner-url", request.getOwnerUrl());
		post.addParameter("user-ip", request.getUserIP());
		post.addParameter("article-date", request.getArticleDate());
		post.addParameter("comment-author", request.getCommentAuthor());
		post.addParameter("comment-type", request.getCommentType());
		post.addParameter("comment-content", request.getCommentContent());
		post.addParameter("comment-author-email", request.getCommentAuthorEmail());
		post.addParameter("comment-author-url", request.getCommentAuthorUrl());
		post.addParameter("permalink", request.getPermalink());
		post.addParameter("referrer", request.getReferrer());
		post.addParameter("user-logged-in", request.isUserLoggedIn() ? "true" : "false");
		
		String postResponse = sendRequest(post);
		HashMap parsedResponse = parseResponse(postResponse);
		
		AuditCommentResponse response = new AuditCommentResponse();
		populateDefaultParametersInResponse(response, parsedResponse);
		response.setSignature((String)parsedResponse.get("signature"));
		response.setSpam(Boolean.valueOf((String)parsedResponse.get("spam")));
		response.setSpaminess(Float.valueOf((String)parsedResponse.get("spaminess")));
		
		return response;
	}
	
	/**
	 * API call to announce-article
	 * @param request
	 * @return
	 * @throws DefensioException
	 */
	public DefensioResponse announceArticle(AnnouneArticleRequest request)  throws DefensioException {
		PostMethod post = new PostMethod(request.getFullURL());
		post.addParameter("owner-url", request.getOwnerUrl());
		post.addParameter("article-author", request.getArticleAuthor());
		post.addParameter("article-author-email", request.getArticleAuthorEmail());
		post.addParameter("article-title", request.getArticleTitle());
		post.addParameter("article-content", request.getArticleContent());
		post.addParameter("permalink", request.getPermalink());
		
		String postResponse = sendRequest(post);
		HashMap parsedResponse = parseResponse(postResponse);
		
		DefensioResponse response = new DefensioResponse();
		populateDefaultParametersInResponse(response, parsedResponse);
		
		return response;
	}
	
	/**
	 * API call to report-false-negatives
	 * @param request
	 * @return
	 * @throws DefensioException
	 */
	public DefensioResponse reportFalseNegatives(ReportFalseNegativesRequest request) throws DefensioException {
		PostMethod post = new PostMethod(request.getFullURL());
		post.addParameter("owner-url", request.getOwnerUrl());
		
		String strSignatures = "";
		for (Iterator iter = request.getSignatures().iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			strSignatures += element + ";";
		}
		strSignatures = strSignatures.substring(0, strSignatures.length() - 1);
		post.addParameter("signatures", strSignatures);
		
		String postResponse = sendRequest(post);
		HashMap parsedResponse = parseResponse(postResponse);
		
		DefensioResponse response = new DefensioResponse();
		populateDefaultParametersInResponse(response, parsedResponse);
		
		return response;
	}
	
	/**
	 * API call to report-false-positives
	 * @param request
	 * @return
	 * @throws DefensioException
	 */
	public DefensioResponse reportFalsePositives(ReportFalsePositivesRequest request) throws DefensioException {
		PostMethod post = new PostMethod(request.getFullURL());
		post.addParameter("owner-url", request.getOwnerUrl());
		
		String strSignatures = "";
		for (Iterator iter = request.getSignatures().iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			strSignatures += element + ";";
		}
		strSignatures = strSignatures.substring(0, strSignatures.length() - 1);
		post.addParameter("signatures", strSignatures);
		
		String postResponse = sendRequest(post);
		HashMap parsedResponse = parseResponse(postResponse);
		
		DefensioResponse response = new DefensioResponse();
		populateDefaultParametersInResponse(response, parsedResponse);
		
		return response;
	}
	
	/** 
	 * API call to get-stats
	 * @param request
	 * @return
	 * @throws DefensioException
	 */
	public GetStatsResponse getStats(GetStatsRequest request) throws DefensioException {
		PostMethod post = new PostMethod(request.getFullURL());
		post.addParameter("owner-url", request.getOwnerUrl());
		
		String postResponse = sendRequest(post);
		HashMap parsedResponse = parseResponse(postResponse);
		
		GetStatsResponse response = new GetStatsResponse();
		populateDefaultParametersInResponse(response, parsedResponse);
		response.setAccuracy(Float.valueOf((String)parsedResponse.get("accuracy")));
		response.setNbFalseNegatives(Integer.valueOf((String)parsedResponse.get("false-negatives")));
		response.setNbFalsePositives(Integer.valueOf((String)parsedResponse.get("false-positives")));
		response.setNbHam(Integer.valueOf((String)parsedResponse.get("ham")));
		response.setNbSpam(Integer.valueOf((String)parsedResponse.get("spam")));
			
		return response;
	}
}
