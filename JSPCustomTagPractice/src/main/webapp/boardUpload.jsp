<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    		import="com.github.chaekwonsoo.webboard.*"%>
<jsp:useBean id="boardManager" class="com.github.chaekwonsoo.webboard.BoardManager"/>
<jsp:useBean id="boardData" class="com.github.chaekwonsoo.webboard.BoardData"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int currentPage = 0;
	int ref = 0;
	int step = 0;
	int depth = 0;
	int num = 0;
	
	String boardName = request.getParameter("boardName");
	String name = request.getParameter("name");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	String password = request.getParameter("password");
	String reply = request.getParameter("reply");
	
	if(reply.equals("ok")) { // 답변글임을 의미한다.
		currentPage = Integer.parseInt(request.getParameter("page"));
		ref = Integer.parseInt(request.getParameter("ref"));
		step = Integer.parseInt(request.getParameter("step"));
		depth = Integer.parseInt(request.getParameter("depth"));
	}
	
	boardData.setNum(num);
	boardData.setName(name);
	boardData.setSubject(subject);
	boardData.setContent(content);
	boardData.setPassword(password);
	boardData.setRef(ref);
	boardData.setStep(step);
	boardData.setDepth(depth);
	
	if(reply.equals("ok")) {	// 답변글일 경우
		boardManager.replyNotice(boardName, boardData);
		if(boardManager.getSuccess()) {
			boardManager.setSuccess(false);
			response.sendRedirect("boardList.jsp?boardName=" + boardName + "&page=" + currentPage);
		}
	} else { // 새로운 글일 경우
		boardManager.writeNotice(boardName, boardData);
		if(boardManager.getSuccess()) {
			boardManager.setSuccess(false);
			response.sendRedirect("boiardList.jsp?boardName=" + boardName + "&page=1&search=&text=");
		}
	}
%>
</body>
</html>