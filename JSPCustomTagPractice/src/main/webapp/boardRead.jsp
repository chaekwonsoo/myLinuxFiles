<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.webboard.*"%>
<jsp:useBean id="boardManager" class="com.github.chaekwonsoo.webboard.BoardManager"/>
<%
	String search = "";
	String text = "";
	
	String currentPage = request.getParameter("page");
	String boardName = request.getParameter("boardName");
	int num = Integer.parseInt(request.getParameter("num"));
	
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
<title>Insert title here</title>
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
		<a href="boardDelete.jsp?boardName=<%=boardName%>&num=<%=boardData.getNum()%>&page=<%=currentPage%>">Delete</a>
		<a href="boardList.jsp?boardName=<%=boardName%>&page=<%=currentPage%>&search=<%=search%>&text=<%=text%>">List</a>
		<a href="boardWrite.jsp?boardName=<%=boardName%>">Write</a>
	</form>
</body>
</html>