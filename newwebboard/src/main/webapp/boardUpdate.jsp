<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.newwebboard.*"%>
<jsp:useBean id="boardManager" class="com.github.chaekwonsoo.newwebboard.BoardManager"/>
<jsp:useBean id="editData" class="com.github.chaekwonsoo.newwebboard.BoardData"/>
<%
	String boardName = (String)request.getParameter("boardName");
	int num = Integer.parseInt((String)request.getParameter("num"));
	String currentPage = (String)request.getParameter("page");
	
	String flag = "false";
	flag = (request.getParameter("flag") == null) ? "false" : request.getParameter("flag");
	if(flag.equals("true")) {
		boardManager.setSuccess(true);
	}
	
	String password = new String();
	password = request.getParameter("password");
	String noticePassword = boardManager.getPassword(boardName, num);
	if(password != null && (password.trim()).equals(noticePassword)) {
		boardManager.setSuccess(true);
	}
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update a writing.</title>
</head>
<body>
	<h3><%=boardName%></h3><hr>
<%
	if(boardManager.getSuccess()) {
		boardManager.setSuccess(false);
		BoardData boardData = boardManager.getNotice(boardName, num);
%>
		<form name="write" method="post" action="boardUpdate.jsp?boardName=<%=boardName%>">
			<input type="hidden" name="flag" value="true">
			<input type="hidden" name="num" value="<%=boardData.getNum()%>">
			<input type="hidden" name="page" value="<%=currentPage%>">
			name: <input type="text" name="name" size="16" value="<%=boardData.getName()%>"><br>
			pswd: <input type="password" name="password" value="<%=boardData.getPassword()%>"><br>
			title: <input type="text" name="subject" size="50" value="<%=boardData.getSubject()%>"><br>
			content: <textarea name="content" cols="50" rows="10"><%=boardData.getContent()%></textarea><br>
			<input type="submit" value="Update">
		</form>
<%
		editData.setNum(num);
		editData.setName(request.getParameter("name"));
		editData.setSubject(request.getParameter("subject"));
		editData.setContent(request.getParameter("content"));
		if(flag.equals("true")) {
			boardManager.updateNotice(boardName, editData);
			if(boardManager.getSuccess()) {
				boardManager.setSuccess(false);
				response.sendRedirect("boardList.jsp?boardName=" + boardName + "&page=" + currentPage);
			} else {
				out.println("<script>alert('The writing has not been updated.');</script>");
				response.sendRedirect("boardRead.jsp?boardName=" + boardName + "&page=" + currentPage + "&num=" + ((String)request.getParameter("num")));
			}
		}
	} else {
%>
		<form name="chkPass" method="post" action="boardUpdate.jsp?boardName=<%=boardName%>&num=<%=num%>&page=<%=currentPage%>">
			Password check is needed to update this writing.<br>
			<input type="password" name="password"><input type="submit" value="Verify">
		</form>
<%
	}
%>
</body>
</html>