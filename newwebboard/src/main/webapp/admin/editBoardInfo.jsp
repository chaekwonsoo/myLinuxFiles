<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.newwebboard.AdminManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit the subject of a board.</title>
</head>
<body>
<%
	AdminManager adminManager = AdminManager.getInstance();
	if(session.getAttribute("id") != null &&
		adminManager.isAdmin(((String)session.getAttribute("id")))) {
		String boardName = request.getParameter("boardName");
		String boardSubject = request.getParameter("boardSubject");
		adminManager.updateBoardSubject(boardName, boardSubject);
		
		response.sendRedirect("printBoardList.jsp");
	}
%>
</body>
</html>