<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.webboard.AdminManager"
    import="java.util.Hashtable"
    import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Print the list of all boards.</title>
</head>
<body>
<%
	AdminManager adminManager = AdminManager.getInstance();
	if(session.getAttribute("id") != null &&
		adminManager.isAdmin(((String)session.getAttribute("id")))) {
%>
		<b>BOARD LIST</b>
<%	
		Hashtable<String, String> table = adminManager.getBoardList();
		Enumeration<String> enumeration = table.keys();
		while(enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			String value = table.get(key);
%>		
			<form action="editBoardInfo.jsp" method="post" name="edit">
				<input type="text" name="boardName" size="20" value="<%=key%>" readonly="readonly"><br>
				<textarea name="boardSubject" cols="35" rows="3"><%=value%></textarea><br>
				<a href="boardList.jsp?boardName=<%=key%>" target="_BLANK">Read</a>
				<a href="javascript:document.edit.submit()">Change</a>
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