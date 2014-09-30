<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    		import="com.github.chaekwonsoo.newwebboard.*"
    		import="com.github.chaekwonsoo.newwebboard.database.SqlMethodBucket"%>

<!-- useBean tag를 사용해서 규약되로 구현된 자바 빈 클래스를 생성하는 방식이 더 좋다고 들었다.
	  지금은 일단 내가 만든 자바 클래스들이 그 규약을 충실히 따르고있지 않기 때문에 그냥 자바코드에서
	  인스턴스를 생성한다.
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actually write a new writing.</title>
</head>
<body>
<%
	BoardManager boardManager = BoardManager.getInstance(
										SqlMethodBucket.getInstance(
												AdminManager.getDataSource()));
	BoardData boardData = new BoardData();

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
			response.sendRedirect("boardList.jsp?boardName=" + boardName + "&page=1&search=&text=");
		}
	}
%>
</body>
</html>