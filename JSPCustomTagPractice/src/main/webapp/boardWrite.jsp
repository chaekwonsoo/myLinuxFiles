<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.webboard.BoardData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String boardName = request.getParameter("boardName");
	String currentPage = request.getParameter("page");
	String reply = "no";
	String ref = "";
	String step = "";
	String depth = "";
	String subject = "";
	String content = "";
	String contentText = "";
	
	int num = 0;
	BoardData data = null;
	
	if(request.getParameter("reply") != null && request.getParameter("reply").equals("ok")) {
		num = Integer.parseInt(request.getParameter("num"));
		reply = request.getParameter("reply");
		ref = request.getParameter("ref");
		step = request.getParameter("step");
		depth = request.getParameter("depth");
		subject = "[Re] " + request.getParameter("subject");
		content = request.getParameter("content");
		contentText = "::::::::: original writing ::::::::::" + System.getProperty("line.separator");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3><%=boardName%></h3>
	<form name="write" method="post" action="boardUpload.jsp?boardName=<%=boardName%>">
		<input type="hidden" name="page" value="<%=currentPage%>">
		<input type="hidden" name="num" value="<%=num%>">
		<input type="hidden" name="ref" value="<%=ref%>">
		<input type="hidden" name="step" value="<%=step%>">
		<input type="hidden" name="depth" value="<%=depth%>">
		<input type="hidden" name="reply" value="<%=reply%>">
		name: <input type="text" name="name" size="16"><br>
		pswd: <input type="password" name="password" ><br>
		title: <input type="text" name="subject" size="50" value="<%=subject%>"><br>
		content: <textarea name="content" cols="50" rows="10"><%out.print(contentText);out.print(content);%></textarea><br>
		<input type="submit" value="Write">
	</form>

</body>
</html>