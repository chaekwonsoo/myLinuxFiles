<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.github.chaekwonsoo.webboard.BoardManager"
    import="com.github.chaekwonsoo.webboard.BoardData"
    import="java.util.Vector"%>
<%
	String boardName = request.getParameter("boardName");
	String search = "";
	String text = "";
	BoardManager boardManager = new BoardManager();
	
	if(request.getParameter("text") != null) {// 어떠한 검색어가 전달되었다는 것.
		search = (String)request.getParameter("search");
		if(request.getParameter("kor") != null) {	// TODO: ??
			text = request.getParameter("text");
		} else {
			text = request.getParameter("text");
		}
	}
	boardManager.setSearch(search);
	boardManager.setText(text);
	
	int currentPage = 1;
	if(request.getParameter("page") != null) {
		currentPage = Integer.parseInt(request.getParameter("page"));
	}
	String paging = boardManager.getPaging(boardName, currentPage);
	Vector<BoardData> vector = boardManager.getPage(boardName, currentPage);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3><%=boardName%></h3><hr>
<%
	BoardData boardData = null;
	if(vector.size() > 0) {
		int depth = 0;
		for(int i = 0; i < vector.size(); i++) {
			boardData = (BoardData)vector.elementAt(i);
%>
			number: <%=boardData.getNum()%><br>
<%
			depth = boardData.getDepth();
			while(depth > 0) {
				depth--;
				out.println("&nbsp;&nbsp;");
			}
%>			<!-- search와 text는 글 하나를 읽고도 이전의 검색 조건을 그대로 유지하기 위함. -->
			title: <a href="boardRead.jsp?boardName=<%=boardName%>&page=<%=currentPage%>&num=<%=boardData.getNum()%>&search=<%=search%>&text=<%=text%>"><%=boardData.getSubject()%></a><br>
			writer: <%=boardData.getName()%><br>
			date: <%=boardData.getDate()%><br>
			viewCount: <%=boardData.getCount()%><br>
			<hr>
<%
		}
	} else {
%>
		There is no writing to show.<br>
<%
	}
%>
	<p><%=paging%></p>
	<form name="searchForm" method="post" action="boardList.jsp?boardName=<%=boardName%>">
		<input type="hidden" name="kor" value="ok">
		<select name="searchSelect" onChange="" align="center">
			<option selected value="subject">title</option>
			<option value="content">content</option>
			<option value="name">writer</option>
		</select>
		<input type="text" name="text">
		<input type="submit" name="searchSubmit" value="Search">
	</form>
	<a href="boardList.jsp?boardName=<%=boardName%>">Back to list</a>
	<a href="boardWrite.jsp?boardName=<%=boardName%>">Write</a>
</body>
</html>