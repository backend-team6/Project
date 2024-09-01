package com.grepp.model;

public class BoardDTO {
    private int no;
    private String writer;
    private int readCount;
    private String title;
    private String content;
    private String regDate;

    public BoardDTO() {
    }

    public BoardDTO(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public BoardDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getNo() {
        return no;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "no=" + no +
                ", writer='" + writer + '\'' +
                ", readCount=" + readCount +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", regDate='" + regDate + '\'' +
                '}';
    }
}
