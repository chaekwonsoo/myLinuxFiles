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
	String boardName = request.getParameter("boardName");
	String boardSubject = request.getParameter("boardSubject");
	
	AdminManager adminManager = AdminManager.getInstance();
	if(boardName.equals("")) {
%>
		A board name is a must.<br>
<%
	} else {
		if(!adminManager.existBoard(boardName)) {
			System.out.println(boardName);
			System.out.println(boardSubject);
			adminManager.makeBoard(boardName, boardSubject);
		} else {
%>
			Same board name already exists.<br>
			Type another name.<br>
<%
		}
	}
%>
	done.
</body>
</html>