<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.newwebboard.AdminManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>admin: Log in.</title>
	</head>
	
	<body>
	
<% 
	AdminManager adminManager = AdminManager.getInstance();
	// Check session.
	if(session.getAttribute("id") != null &&
		adminManager.isAdmin(((String)session.getAttribute("id")))) {
		response.sendRedirect("printBoardList.jsp");
	} else {
%>
		<p align="center">YEWS</p>
		<p align="center">Log in as an administrator.</p>
<%
		String adminId = request.getParameter("adminId");
		String adminPW = request.getParameter("adminPW");
		if(adminManager.checkAdmin(adminId, adminPW)) {
			session.setAttribute("id", adminId);
			response.sendRedirect("adminLogin.jsp");
		} else {
%>
			<br><br>
			<form action="adminLogin.jsp" method="post">
				<div align="center">
					ID : <input type="text" name="adminId"><br>
					PW : <input type="password" name="adminPW"><br>
					<input type="submit" value="login">
				</div>
			</form>
<%
		}
	}
%>
	</body>
</html>