package com.ui.ikonoklastik.roller.ui.plugins.comments.defensio;

import org.apache.roller.weblogger.pojos.WeblogEntryComment;
import org.apache.roller.weblogger.ui.rendering.plugins.comments.CommentValidator;
import org.apache.roller.weblogger.util.RollerMessages;

/**
 *
 * @author alexbcoles
 */
public class DefensioPlugin implements CommentValidator {

    public static final String NAME = "Defensio";
    
    public String getName() {
        return NAME;
    }

    public int validate(WeblogEntryComment arg0, RollerMessages arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
