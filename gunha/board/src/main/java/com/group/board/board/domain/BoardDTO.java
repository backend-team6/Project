package com.group.board.board.domain;

import java.sql.Date;

public class BoardDTO {
	private int no;
	private String writer;
	private int readCount;
	private String title;
	private String content;
	private Date regDate;

	public BoardDTO(int no, String writer, int readCount, String title, String content, Date regDate) {
		this.no = no;
		this.writer = writer;
		this.readCount = readCount;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
	}

	public BoardDTO(String title, String writer, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regDate = new Date(System.currentTimeMillis());

	}

	public int getNo() {
		return no;
	}

	public String getWriter() {
		return writer;
	}

	public int getReadCount() {
		return readCount;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getRegDate() {
		return regDate;
	}
}
