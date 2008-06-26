package com.ui.ikonoklastik.roller.ui.plugins.comments.defensio;

import com.defensio.DefensioWrapper;
import com.defensio.exceptions.DefensioException;
import com.defensio.requests.AuditCommentRequest;
import com.defensio.responses.AuditCommentResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.roller.weblogger.business.WebloggerFactory;
import org.apache.roller.weblogger.config.WebloggerConfig;
import org.apache.roller.weblogger.pojos.WeblogEntry;
import org.apache.roller.weblogger.pojos.WeblogEntryComment;
import org.apache.roller.weblogger.ui.rendering.plugins.comments.CommentValidator;
import org.apache.roller.weblogger.util.RollerMessages;

/**
 *
 * @author alexbcoles
 */
public class DefensioPlugin implements CommentValidator {

    private static final Log log = LogFactory.getLog(DefensioPlugin.class);

    public static final String NAME = "Defensio";
    public static final String API_VERSION = "1.2";

    private DefensioWrapper defensio = new DefensioWrapper();
    private String apiKey;

    public DefensioPlugin() {
        apiKey = WebloggerConfig.getProperty("comment.validator.defensio.apikey");
    }

    public String getName() {
        return NAME;
    }

    public int validate(WeblogEntryComment comment, RollerMessages messages) {

        WeblogEntry entry = comment.getWeblogEntry();
        String blogUrl = WebloggerFactory.getWeblogger().getUrlStrategy().getWeblogURL(entry.getWebsite(), null, true);
        AuditCommentRequest request = new AuditCommentRequest();

        request.setApiVersion(API_VERSION);
        request.setApiKey(apiKey);
        request.setOwnerUrl(blogUrl);
        request.setUserIP(comment.getRemoteHost());  // roller API may also provide hostname
        request.setArticleDate(entry.getPubTime().toString());
        request.setCommentAuthor(comment.getName());
        request.setCommentAuthorEmail(comment.getEmail());
        request.setCommentAuthorUrl(comment.getUrl());
        request.setCommentContent(comment.getContent());
        request.setCommentType("comment"); // could be trackback, pingback, other
        request.setPermalink(entry.getPermalink());
        request.setReferrer(comment.getReferrer());
        //request.setUserLoggedIn(userLoggedIn); -- not sure how to test for this
        try {
            AuditCommentResponse response = defensio.auditComment(request);
            int spaminess = (int) (response.getSpaminess() * 100);

            if (response.isSpam()) {
                messages.addMessage("Defensio reported this comment as spam");
                return spaminess; // or 0 ?;
            } else {
                return 100;
            }
        } catch (DefensioException ex) {
            log.error("ERROR checking comment against Defensio", ex);
        }

        return 0; // interpret error as spam: better safe than sorry?
    }

}
