package com.github.chaekwonsoo.webboard.customtags;
/*
 * 이처럼, (custom) tag를 사용해서 할 수 있는 기능을 구현해놓은 것을 'Tag Handler'라고 한다. 
 */
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class HelloTagInterface implements Tag {

	PageContext pageContext;
	Tag parent;
	
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write("Hello custom tag!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;	// custom tag 가 가지고 있는 컨텐츠를 처리하지 않겠다.
	}
	
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public Tag getParent() {
		return parent;
	}

	public void release() {}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public void setParent(Tag parent) {
		this.parent = parent;
	}
	
}
