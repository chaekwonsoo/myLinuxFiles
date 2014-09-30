package com.github.chaekwonsoo.webboard;

/*
 * This class represents each writing.
 */

import java.io.Serializable;
import java.util.Date;

public class BoardData implements Serializable {
	private int num;
	private String name;
	private String subject;
	private String content;
	// instead of Date, use Joda time.
	private Date date;
	private String password;
	private int count;
	
	private int ref;			// (답변) 글이 참조하는 원문 번호
	private int step;			// 답변 글의 출력 순서
	private int depth;		// 답변 글의 깊이
	private int childCount;	// 답변 글의 수
	
	public void setNum(int num) {
		this.num = num;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	
	public int getNum() {
		return num;
	}
	public String getName() {
		return name;
	}
	public String getSubject() {
		return subject;
	}
	public String getContent() {
		return content;
	}
	public Date getDate() {
		return date;
	}
	public String getPassword() {
		return password;
	}
	public int getCount() {
		return count;
	}
	public int getRef() {
		return ref;
	}
	public int getStep() {
		return step;
	}
	public int getDepth() {
		return depth;
	}
	public int getChildCount() {
		return childCount;
	}
}
