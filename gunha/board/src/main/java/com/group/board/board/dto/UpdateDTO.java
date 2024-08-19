package com.group.board.board.dto;

import java.sql.Date;

public class UpdateDTO {
	private int no;
	private String title;
	private String content;
	private Date regDate;

	public UpdateDTO(int no, String title, String content) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.regDate = new Date(System.currentTimeMillis());
	}

	public int getNo() {
		return no;
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
