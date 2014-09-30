<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.newwebboard.BoardManager"
    import="com.github.chaekwonsoo.newwebboard.AdminManager"
    import="com.github.chaekwonsoo.newwebboard.database.SqlMethodBucket"%>
<%
	BoardManager boardManager = BoardManager.getInstance(
										SqlMethodBucket.getInstance(
												AdminManager.getDataSource()));
	String currentPage = request.getParameter("page");
	String boardName = request.getParameter("boardName");
	int num = Integer.parseInt(request.getParameter("num"));
	String password = request.getParameter("password");
	
	String search = request.getParameter("search");
	String text = request.getParameter("text");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete a writing.</title>
</head>
<body>
	<h3><%=boardName%></h3><hr>
	<form name="chkPass" method="post" action="boardDelete.jsp?boardName=<%=boardName%>&num=<%=num%>&page=<%=currentPage%>">
<%
		if(request.getParameter("password") != null) {
			String noticePassword = boardManager.getPassword(boardName, num);
			if(!noticePassword.equals(password)) {
				session.setAttribute("canDelete", "false");
				response.sendRedirect("boardRead.jsp?boardName=" + boardName + "&page=" + currentPage + "&num=" + num
																		+ "&search=" + search + "&text=" + text);
			} else {
				String msg = boardManager.deleteNotice(boardName, num);
				if(boardManager.getSuccess()) {
					boardManager.setSuccess(false);
					response.sendRedirect("boardList.jsp?boardName=" + boardName + "&page=" + currentPage);
				} else {
					out.println("<script>alert('" + msg + "');</script>");
				}
			}
		} else {
%>
			You need the proper password to delete this writing.<br>
			<input type="password" name="password"> <input type="submit" value="Delete">
<%
		}
%>
	</form>
</body>
</html>
