package kr.board.entity;

import lombok.Data;

@Data
public class Board {
	
	private int idx;
	private String memID; // 회원 아이디
	private String title;
	private String content;
	private String writer;
	private String indate;
	private int count;
	
}
