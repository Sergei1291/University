package com.epam.university.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public abstract class AbstractTag<T> extends SimpleTagSupport {

    private StringWriter sw = new StringWriter();

    protected abstract T getName(int id);

    public void doTag() throws JspException, IOException {
        getJspBody().invoke(sw);
        StringBuffer data = sw.getBuffer();
        Integer id = Integer.parseInt(new String(data));
        T name = getName(id);
        getJspContext().getOut().println(name);
    }

}