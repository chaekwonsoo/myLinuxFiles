<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.webboard.AdminManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>the Login page for the administrator</title>
	</head>
	
	<body>
<% 
	AdminManager adminManager = AdminManager.getInstance();
	// Check session.
	if(session.getAttribute("id") != null &&
		adminManager.isAdmin(((String)session.getAttribute("id")))) {
%>
		<!-- 실제로는 이 메시지 대신, 게시판 관리를 할 수 있는 페이지(코드)를 작성하면 된다. -->
		You are logged in as the administrator.<br>
		<a href="logout.jsp">Log out</a>
<%
	} else {
		String adminId = request.getParameter("adminId");
		String adminPW = request.getParameter("adminPW");
		if(adminManager.checkAdmin(adminId, adminPW)) {
			session.setAttribute("id", adminId);
			response.sendRedirect("adminLogin.jsp");
		} else {
%>
			<form action="adminLogin.jsp" method="post">
				ID : <input type="text" name="adminId"><br>
				PW : <input type="password" name="adminPW"><br>
				<input type="submit" value="login">
			</form>
<%
		}
	}
%>
	</body>
</html>