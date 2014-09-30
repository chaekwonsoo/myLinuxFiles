package com.github.chaekwonsoo.webboard.customtags;
/*
 * 이처럼, (custom) tag를 사용해서 할 수 있는 기능을 구현해놓은 것을 'Tag Handler'라고 한다. 
 */
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

// TagSupport 클래스는 Tag 인터페이스를 구현한다.
// Tag 인터페이스를 구현하는 방식과는 달리, 필요한 메소드만 재정의하면 된다.
public class HelloTagSupportClass extends TagSupport {
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write("Hello custom tag..!");
		} catch(IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}