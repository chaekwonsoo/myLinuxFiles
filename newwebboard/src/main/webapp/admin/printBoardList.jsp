<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.newwebboard.AdminManager"
    import="java.util.Hashtable"
    import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>admin: Print the list of all boards.</title>
</head>
<body>
<%
	AdminManager adminManager = AdminManager.getInstance();
	if(session.getAttribute("id") != null &&
		adminManager.isAdmin(((String)session.getAttribute("id")))) {
%>
		<b>BOARD LIST: This is the page for administrator.</b>
		<form name="makeBoardForm" method="post" action="makeBoardForm.jsp">
			<input type="submit" value="Make a board" name="makeBoardButton">
		</form>
		<form name="logOutForm" method="post" action="../logout.jsp">
			<input type="submit" value="Admin log out" name="logOutButton">
		</form>
		
<%	
		Hashtable<String, String> table = adminManager.getBoardList();
		Enumeration<String> enumeration = table.keys();
		for(int index = 0; enumeration.hasMoreElements(); index++) {
			String formName = "editForm" + index;
			String key = enumeration.nextElement();
			String value = table.get(key);
%>		
		<form action="editBoardInfo.jsp" method="post" name=<%=formName%>>
			<input type="text" name="boardName" size="20" value="<%=key%>" readonly="readonly"><br>
			<textarea name="boardSubject" cols="35" rows="3"><%=value%></textarea><br>
			<a href="../boardList.jsp?boardName=<%=key%>" target="_BLANK">Read</a>
			<a href="javascript: document.<%=formName%>.submit();">Change</a>
			
			<!-- <a href="#" onclick="document.forms['editForm'].submit();return false;">Change</a> -->
			<!-- <a href="#" onclick="this.form.submit();">Change</a> -->
			<!-- <a href="#" onclick="document.forms[2].submit(); return false;">Change</a> -->	
			<!-- <a href="#" onclick="javascript: document.getElementById('editForm').submit();return false;">Change</a> -->
			<!-- <input type="submit" value="change" name="editFormSubmit"> -->	<!-- It works!! -->
			
			<a href="deleteBoard.jsp?boardName=<%=key%>">Delete</a>
		</form>
<%
		}
	} else {
		out.print("not the administrator.");
	}
%>
</body>
</html>