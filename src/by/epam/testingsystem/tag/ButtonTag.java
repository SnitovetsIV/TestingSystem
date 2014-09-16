package by.epam.testingsystem.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Tag for representing buttons for switching pages
 *
 * @author Илья
 */
public class ButtonTag extends TagSupport {

    private int step;
    private int position;
    private int size;

    public void setStep(int step) {
        this.step = step;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<form name=\"listPages\" action=\"controller\" method=\"post\">");
            out.write("<input type=\"hidden\" name=\"command\" value=\"SWITCH_PAGE\"/>");
            for (int i = 1; i <= (size / (step + 0.000000000000001) + 1); i++) {
                out.write("<button ");
                int currentPos = (i - 1) * step;
                if (position == currentPos) {
                    out.write("disabled class=\"current-page-button\" ");
                } else {
                    out.write("class=\"page-button\" ");
                }
                out.write("name=\"startList\" value=\"" + currentPos + "\">");
                out.write(i + "</button>");
            }
            out.write("</form>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
