<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.newwebboard.UserManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>user: Log in.</title>
	</head>
	<body>
	
<% 
	// AdminManager가 아닌 UserManager 클래스를 따로 만들어야 할 것 같다..
	UserManager userManager = UserManager.getInstance();
	// Check session.
	if(session.getAttribute("id") != null &&
		userManager.isUser(((String)session.getAttribute("id")))) {
		response.sendRedirect("showAllBoards.jsp");
	} else {
%>
		<p align="center"><b>YEWS</b></p>
		<p align="center">Sign in: Enjoy and share your news!</p>
<%
		String userId = request.getParameter("userId");
		String userPW = request.getParameter("userPW");
		if(userManager.checkUser(userId, userPW)) {			//TODO: user마다 각기 자기네 정보를 볼 수 있게 해야 한다.
			session.setAttribute("id", userId);
			response.sendRedirect("userLogin.jsp");
		} else {
%>
			<br><br>
			<form action="userLogin.jsp" method="post">
				<div align="center">
					ID : <input type="text" name="userId"><br>
					PW : <input type="password" name="userPW"><br>
					<input type="submit" value="Sign in">
				</div>
			</form>
<%
		}
	}
%>
	</body>
</html>