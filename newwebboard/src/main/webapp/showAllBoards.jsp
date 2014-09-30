<!-- 
This is the main page for users.
Right after users sign in, they see this page. 
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.newwebboard.UserManager"
    import="java.util.Hashtable"
    import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show all the boards in the web site.</title>
</head>
<body>
<%
	UserManager userManager = UserManager.getInstance();
	if(session.getAttribute("id") != null &&
		userManager.isUser(((String)session.getAttribute("id")))) {
%>
		<b>BOARD LIST</b>
		
		<form name="logOutForm" method="post" action="logout.jsp">
			<input type="submit" value="Sign out" name="logOutButton">
		</form>
		
		<!-- TODO: Other things that users can do on their main pages. -->
		
<%
		Hashtable<String, String> table = userManager.getBoardList();
		Enumeration<String> enumeration = table.keys();
		for(int index = 0; enumeration.hasMoreElements(); index++) {
			String formName = "editForm" + index;
			String key = enumeration.nextElement();
			String value = table.get(key);
%>
			<input type="text" name="boardName" size="20" value="<%=key%>" readonly="readonly"><br>
			<textarea name="boardSubject" cols="35" rows="3" readonly="readonly"><%=value%></textarea><br>
			<a href="boardList.jsp?boardName=<%=key%>" target="_BLANK">Read</a><br><br>
<%
		}
	} else {
		out.print("not an user.");
	}
%>
</body>
</html>