package com.epam.university.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class HelloTag extends SimpleTagSupport {

    StringWriter sw = new StringWriter();

    public void doTag() throws JspException, IOException {

        getJspBody().invoke(sw);
        getJspContext().getOut().println(sw.toString() + "sssdsds");

    }

}