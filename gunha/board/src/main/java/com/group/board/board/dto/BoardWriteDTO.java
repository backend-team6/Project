package com.group.board.board.dto;

import java.sql.Date;

public class BoardWriteDTO {
	private String title;
	private String writer;
	private String content;
	private Date regDate;

	public BoardWriteDTO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	public String getWriter() {
		return writer;
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
