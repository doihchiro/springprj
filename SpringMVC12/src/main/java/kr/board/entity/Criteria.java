package kr.board.entity;

import lombok.Data;

@Data
public class Criteria {
	
	private int page;		// 현재 페이지 번호
	private int perPageNum; // 한페이지에 보여줄 게시글의 수

	// 검색 기능에 필요한 변수 추가
	private String type;
	private String keyword;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 5; // 조정
	}
	
	// 현재 페이지의 게시글 인덱스 번호를 구하는 메서드
	public int getPageStart() {			// 1page  2page  3page
		return (page - 1) * perPageNum; // 0~     10~    20~   : limit ${pageStart},#{perPageNum}
	}
	
}
