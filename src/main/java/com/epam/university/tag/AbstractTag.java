package com.epam.university.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Optional;

public abstract class AbstractTag<T> extends SimpleTagSupport {

    private StringWriter sw = new StringWriter();

    protected abstract Optional<T> getName(int id);

    public void doTag() throws JspException, IOException {
        getJspBody().invoke(sw);
        StringBuffer data = sw.getBuffer();
        Integer id = Integer.parseInt(new String(data));
        Optional<T> optionalName = getName(id);
        if (!optionalName.isPresent()) {
            getJspContext().getOut().println("UNKNOWN");
            return;
        }
        T name = optionalName.get();
        getJspContext().getOut().println(name);
    }

}