<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.newwebboard.AdminManager"
    import="com.github.chaekwonsoo.newwebboard.BoardManager"
    import="com.github.chaekwonsoo.newwebboard.BoardData"
    import="com.github.chaekwonsoo.newwebboard.database.SqlMethodBucket"%>
	
	<!-- 원래 이렇게 useBean 태그로 하려고했는데, BoardManager의 생성자를 인자를 가지도록 구현하는 바람에 이렇게 못함.. -->
<%
	BoardManager boardManager = BoardManager.getInstance(
										SqlMethodBucket.getInstance(
												AdminManager.getDataSource()));
	String search = "";
	String text = "";
	
	String currentPage = request.getParameter("page");
	String boardName = request.getParameter("boardName");
	int num = Integer.parseInt(request.getParameter("num"));
	String delete = request.getParameter("delete");
	
	if(request.getParameter("text") != null) {
		search = request.getParameter("search");
		text = request.getParameter("text");
	}
	BoardData boardData = boardManager.getNotice(boardName, num);
	int[] maxMin = boardManager.getNextPrevNum(boardName, num);
	BoardData preData;
	BoardData nextData;
	
	if(maxMin[0] <= 0 && maxMin[1] <= 0) {
		preData = null;
		nextData = null;
	} else if(maxMin[0] <= 0) {
		preData = boardManager.getNotice(boardName, maxMin[1]);
		nextData = null;
	} else if(maxMin[1] <= 0) {
		preData = null;
		nextData = boardManager.getNotice(boardName, maxMin[0]);
	} else {
		preData = boardManager.getNotice(boardName, maxMin[1]);
		nextData = boardManager.getNotice(boardName, maxMin[0]);
	}
	
	boardManager.increaseCnt(boardName, num);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Read a writing.</title>
</head>
<body>

	<h3><%=boardName%></h3><hr>
	<form name="replyForm" action="boardWrite.jsp?boardName=<%=boardName%>" method="post">
		<input type="hidden" name="subject" value="<%=boardData.getSubject()%>">
		<input type="hidden" name="content" value="<%=boardData.getContent()%>">
		<input type="hidden" name="num" value="<%=boardData.getNum()%>">
		<input type="hidden" name="page" value="<%=currentPage%>">
		<input type="hidden" name="reply" value="ok">
		<input type="hidden" name="ref" value="<%=boardData.getRef()%>">
		<input type="hidden" name="step" value="<%=boardData.getStep()%>">
		<input type="hidden" name="depth" value="<%=boardData.getDepth()%>">
		number: <%=num%><br>
		title: <%=boardData.getSubject()%><br>
		writer: <%=boardData.getName()%><br>
		viewCount: <%=boardData.getCount()%><br>
		date: <%=boardData.getDate()%><br>
		<pre><%=boardData.getContent()%></pre><hr>
<%
		if(maxMin[1] != 0) {
%>
			previous: <a href="boardRead.jsp?boardName=<%=boardName%>&num=<%=maxMin[1]%>&page=<%=currentPage%>"><%=preData.getSubject()%></a><br>
<%
		} else {
%>
			There is no previous writing.<br>
<%
		}
		if(maxMin[0] != 0) {
%>
			next: <a href="boardRead.jsp?boardName=<%=boardName%>&num=<%=maxMin[0]%>&page=<%=currentPage%>"><%=nextData.getSubject()%></a><br>
<%
		} else {
%>
			There is no next writing.<br>
<%
		}
%>
		<hr><a href="javascript:document.replyForm.submit()">reply</a>
		<a href="boardUpdate.jsp?boardName=<%=boardName%>&num=<%=boardData.getNum()%>&page=<%=currentPage%>">Update</a>
		<a href="boardDelete.jsp?boardName=<%=boardName%>&num=<%=boardData.getNum()%>&page=<%=currentPage%>&search=<%=search%>&text=<%=text%>">Delete</a>
		<a href="boardList.jsp?boardName=<%=boardName%>&page=<%=currentPage%>&search=<%=search%>&text=<%=text%>">List</a>
		<a href="boardWrite.jsp?boardName=<%=boardName%>">Write</a>
	</form>
	
<%
	/*
		글을 지울 때 비밀번호가 틀렸다면 여기 다시 글로 돌아와서 alert 메시지를 출력해준다.
		그런데 그냥 boardDelete.jsp에서 response.sendRedirect()를 통해 GET 방식으로 비밀번호 틀렸다는 파라미터를 전달하여
		그 파라미터를 체크해서 alert를 띄워주는 방식을 사용하면, 사용자가 이후 다른 페이지 갔다가 다시 뒤로오면 또 alert창이 뜬다.
		그래서, 이걸 방지하기 위해 sendRedirect() GET방식으로 파라미터 전달하는 대신
		session 객체의 attribute를 이용하여 구현했다 :).
		
		그런데 이때도 다시 write 페이지 갔다가 뒤로버튼 눌러서 돌아오면 또 alert 메시지가 떴다.
		그래서 자바스크립트 hisotry.go(0) 로 해주었더니 괜찮았다.
		history(-1) 하면 이전 페이지로 돌아가기 때문에 맞지 않다.
	*/
	if(session.getAttribute("canDelete") != null && session.getAttribute("canDelete").equals("false")) {
		session.removeAttribute("canDelete");
		out.print("<script>alert('wrong password!');</script>");
		out.print("<script>history.go(0);</script>");
	}
%>
</body>
</html>