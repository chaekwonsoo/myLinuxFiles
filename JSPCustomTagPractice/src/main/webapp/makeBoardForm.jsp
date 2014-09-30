<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.webboard.AdminManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	AdminManager adminManager = AdminManager.getInstance();
	if(session.getAttribute("id") != null && adminManager.isAdmin((String)session.getAttribute("id"))) {
%>
		<b> MAKE BOARD</b>
		<form action="makeBoard.jsp" method="post" name="makeBoardForm">
			<input type="text" name="boardName" size="20"><br>
			<textarea name="boardSubject" cols="35" rows="3"></textarea><br>
			<input type="submit" value="Make a board">
		</form>
<%
	} else {
		out.print("not the administrator");
	}
%>	
	
</body>
</html>