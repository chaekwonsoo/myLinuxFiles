package com.github.chaekwonsoo.webboard;

import java.sql.SQLException;
import java.util.Vector;

public class BoardManager extends AdminManager {
	
	public static BoardManager boardManager = null;
	
	public static final int LINES_PER_PAGE = 10;	// 한 페이지에 나타날 글의 수
	public static final int PAGES_PER_GROUP = 10;	// 한 스크린에 나타날 페이지 링크 수
	private int recordNum;
	private int totalPage;
	private int currentPage;
	
	private String search = "";	// search query
	private String text = "";		// search result
	
	private boolean success;
	
	// single-ton 패턴 적용한다고 이 생성자를 private으로 했더니 jsp:useBean 태그에서 에러 발생했다.
	// 자비 빈의 약속인, 파라미터가 없는 public 생성자가 있어야 한다는 규칙과 관련있었다.
	// private에서 public으로 바꿔주니까 JSP 문제가 해결되었다.
	public BoardManager() {
		super();
	}
	
	public static BoardManager getInstance() {
		if(boardManager == null)
			boardManager = new BoardManager();
		return boardManager;
	}
	
	/**
	 * @param boardName
	 * @return the max number of writing
	 * 
	 * Get the maximum number of writing from the database of the board specified as the parameter.
	 * @throws SQLException 
	 */
	public int getMaxNum(String boardName) throws SQLException {
		String sql = "SELECT max(num) FROM " + boardName;
		return adminExecuteQueryNum(sql);
	}
	
	/**
	 * @param boardName
	 * @param data
	 * 
	 * Write a new writing to the board.
	 * @throws SQLException 
	 */
	public void writeNotice(String boardName, BoardData data) throws SQLException {
		int noticeNum = this.getMaxNum(boardName) + 1;
		String sql = "INSERT INTO " + boardName + " values(" + noticeNum + ", '" 
																	 + data.getName() + "', '"
																	 + data.getSubject() + "', '"
																	 + data.getContent() + "', "
																	 + "now(), '"
																	 + data.getPassword() + "', "
																	 + "0, "
																	 + noticeNum + ", "
																	 + "0, "
																	 + "0, "
																	 + "0)";
		adminExecuteUpdate(sql);
		this.setSuccess(true);
	}
	
	/**
	 * @param boardName
	 * @param num
	 * @return a writing
	 * 
	 * Get a writing from the board specified as the first parameter.
	 * @throws SQLException 
	 */
	public BoardData getNotice(String boardName, int num) throws SQLException {
		String sql = "SELECT * FROM " + boardName + " WHERE num=" + num;
		Vector<BoardData> vector = adminExecuteQuery(sql);
		BoardData data = (BoardData)vector.elementAt(0);
		
		return data;
	}
	
	/**
	 * @param boardName
	 * @param num
	 * @return the password of the writing
	 * 
	 * Get the password from the writing specified.
	 * @throws SQLException 
	 */
	public String getPassword(String boardName, int num) throws SQLException {
		String sql = "SELECT password FROM " + boardName + " WHERE num='" + num + "'";
		return adminExecuteQueryString(sql);
	}
	
	/**
	 * @param boardName
	 * @param data
	 * 
	 * Update the writing specified.
	 * @throws SQLException 
	 */
	public void updateNotice(String boardName, BoardData data) throws SQLException {
		String sql = "UPDATE " + boardName +
					   " SET name='" + data.getName() + 
							"', subject='" + data.getSubject() + 
							"', content='" + data.getContent() +
					   "' WHERE num='" + data.getNum() + "'";
		adminExecuteUpdate(sql);
		this.setSuccess(true);
	}
	
	/**
	 * @param boardName
	 * @param data
	 * 
	 * Write a reply to the writing specified.
	 * @throws SQLException 
	 */
	public void replyNotice(String boardName, BoardData data) throws SQLException {
		// 답변 글이 삽입될 step값 계산
		String sql = "SELECT min(step) FROM " + boardName + "WHERE ref=" + data.getRef() +
																	" and step>" + data.getStep() +
																	" and depth<=" + data.getDepth();
		int mstep = adminExecuteQueryNum(sql);
		int instep = 0;
		
		
		if(mstep > 0) {	// 답변 글이 중간에 끼어들어갈 경우.
			// 하위 답변글들의 step 값들을 1씩 증가
			sql = "UPDATE " + boardName + "SET step=step+1 WHERE ref='" + data.getRef() + "and step>='" + mstep;
			adminExecuteUpdate(sql);
			instep = mstep;
		} else {			// 답변 글이 그냥 맨 마지막에.
			sql = "SELECT max(step) FROM " + boardName + " WHERE ref='" + data.getRef() + "'";
			instep = adminExecuteQueryNum(sql) + 1;
		}
		
		int maxNum = getMaxNum(boardName) + 1;
		int depth = data.getDepth();
		sql = "INSERT INTO " + boardName + " VALUES(" + maxNum + ", '" + data.getName() + "', '" + data.getSubject() + "', '" + 
																data.getContent() + "', now(), '" + data.getPassword() + "', 0, " +
																data.getRef() + ", " + instep + ", " + (++depth) + ", 0)";
		adminExecuteUpdate(sql);
		
		// childCount 수 1씩 증가시키기
		for(int r = depth - 1; r >= 0; r--) {
			sql = "SELECT max(step) FROM " + boardName + " WHERE ref=" + data.getRef() +
																	" and step<" + mstep + 
																	" and depth=" + r;
			int maxStep = adminExecuteQueryNum(sql);
			
			sql = "UPDATE " + boardName + "SET childCount=childCount+1 WHERE ref=" + data.getRef() +
																				" and depth=" + r +
																				" and step=" + maxStep;
			adminExecuteUpdate(sql);
		}
		
		this.setSuccess(true);
	}
	
	/**
	 * @param boardname
	 * @param pageNumber
	 * @return a vector that contains all the writing of the page.
	 * 
	 * Get all the writings as a vector from the page specified.
	 * @throws SQLException 
	 */
	public Vector<BoardData> getPage(String boardName, int pageNumber) throws SQLException {
		String sql = "";
		
		if(!(text.trim()).equals("")) { // 검색 text 변수에 저장된 값을 포함하는 글 목록 리턴
			sql = "SELECT * FROM " + boardName + " WHERE upper(" + search + ") like upper('%" + text + "%')" + 
															" order by ref desc, step limit " + LINES_PER_PAGE * (pageNumber - 1) + ", " + LINES_PER_PAGE;
		} else { // 검색 조건이 없는 일반적인 글 목록 리턴										// 몇 번 글부터								// 몇 개만큼 출력해라.
			sql = "SELECT * FROM " + boardName + " order by ref desc, step limit " + LINES_PER_PAGE * (pageNumber - 1) + ", " + LINES_PER_PAGE;
		}
		
		return adminExecuteQuery(sql);
	}
	
	/**
	 * @param boardName
	 * @param pageNumber
	 * @return HTML code for the links as a string
	 * 
	 * Form the HTML code for the page links and return it as a String.
	 * @throws SQLException 
	 */
	public String getPaging(String boardName, int pageNumber) throws SQLException {
		currentPage = pageNumber;
		int recNum = this.getRecNum(boardName);
		totalPage = ((recNum - 1) / 10) + 1;
		
		// 페이지 링크의 모습 ==> [이전(10)] 11 12 13 ...  ... 19 20 [다음(21)]
		
		String boardLink = "boardList.jsp?boardName=" + boardName;
		// 스크린에 출력될 페이지 링크들 중 첫번째 페이지 (예를 들면, 11)
		int startPage;
		// 스크린에 출력될 페이지 링크들 중 마지막 페이지 (예를 들면, 20) 
		int endPage;
		int linkPage;
		String strList = "";
		
		startPage  = ((pageNumber - 1) / PAGES_PER_GROUP) * PAGES_PER_GROUP + 1;
		endPage = (((startPage - 1) + PAGES_PER_GROUP) / PAGES_PER_GROUP) * PAGES_PER_GROUP;
		
		if(totalPage <= endPage) {
			endPage = totalPage;
		}
		
		if(currentPage > PAGES_PER_GROUP) {
			linkPage =startPage - 1;
			strList += "<a href='" + boardLink + "&page=" + linkPage + "'>[previous]</a>";
		} else {
			strList += "[previous]";
		}
		strList += "...";
		
		linkPage = startPage;
		while(linkPage <= endPage) {
			if(linkPage == currentPage) {
				strList += "&nbsp;" + currentPage + "&nbsp;";
			} else {
				strList += "<a href='" + boardLink + "&page=" + linkPage + "'>[" + linkPage + "]</a>";
			}
			linkPage++;
		}
		strList += "...";
		
		if(totalPage > endPage) {
			linkPage = endPage + 1;
			strList += "<a href='" + boardLink + "&page=" + linkPage + "'>[next]</a>";
		} else {
			strList += "[next]";
		}
		
		return strList;
	}
	
	// 검색 조건 설정 : 제목 / 내용 / 작성자
	public void setSearch(String search) {
		// TODO
	}
	
	// 검색어 설정
	public void setText(String text) {
		// TODO
	}
	
	/**
	 * @param boardName
	 * @param num
	 * @return
	 * 
	 * Policy: If there exists any reply, we can not delete the writing.
	 */
	public String deleteNotice(String boardName, int num) throws SQLException {
		String msg = new String();
		String sql = "SELECT * FROM " + boardName  + " WHERE num=" + num;
		Vector<BoardData> vector = adminExecuteQuery(sql);
		BoardData data = (BoardData)vector.elementAt(0);
		
		int ref = data.getRef();
		int step = data.getStep();
		int depth = data.getDepth();
		int childCount = data.getChildCount();
		
		if(childCount == 0) {	// 답변글이 없을 경우
			sql = "DELETE FROM " + boardName + " WHERE num=" + num;
			adminExecuteUpdate(sql);
			
			for(int r = depth - 1; r >= 0; r--) {
				sql = "SELECT max(step) FROM " + boardName + " WHERE ref=" + ref + 
																		" and depth=" + r +
																		" and step<" + step;
				int maxStep = adminExecuteQueryNum(sql);
				
				sql = "UPDATE " + boardName + " SET childCount=childCount-1 WHERE ref=" + ref +
																					" and depth=" + r + " and step=" + maxStep;
				adminExecuteQuery(sql);
			}
			msg = "OK";
			setSuccess(true);
		} else {	// 답변글이 있을 경우
			msg = "Can't delete this writing: because it has its replies.";
			setSuccess(false);
		}
		return msg;
	}
	
	/**
	 * @param boardName
	 * @param num
	 * 
	 * Increase the view count.
	 */
	public void increaseCnt(String boardName, int num) throws SQLException {
		String sql = "UPDATE " + boardName + " SET count=count+1 WHERE num=" + num;
		adminExecuteUpdate(sql);
	}
	
	public int[] getNextPrevNum(String boardName, int num) throws SQLException {
		int[] nums = new int[2];
		String sql = "SELECT num FROM " + boardName + " WHERE num>" + num + " ORDER BY num DESC";
		nums[0] = adminExecuteQueryNum(sql);
		
		sql = "SELECT num FROM " + boardName + " WHERE num<" + num;
		nums[1] = adminExecuteQueryNum(sql);

		return nums;
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public int getRecNum(String boardName) throws SQLException {
		String sql = null;
		if(!(text.trim()).equals("")) {
			sql = "SELECT count(*) FROM " + boardName + " WHERE upper(" + search + ") like upper('%" + text + "%')";
		} else {
			sql = "SELECT count(*) FROM " + boardName;
		}
		recordNum = adminExecuteQueryNum(sql);
		return recordNum;
	}
}
