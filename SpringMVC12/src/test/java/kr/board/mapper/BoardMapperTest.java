package kr.board.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.board.entity.Board;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {

	@Autowired
	BoardMapper boardMapper;
	
	@Test
	/*
	public void testGetList() {
		List<Board> list = boardMapper.getList();
		
		for (Board board : list) {
//			System.out.println(board);
			log.info(board);
		}
	}*/
	
	public void testInsert() {
		Board board = new Board();
		board.setMemID("loopy");
		board.setTitle("test");
		board.setContent("test 작성글");
		board.setWriter("잔망루피");
//		boardMapper.insert(board);
		boardMapper.insertSelectKey(board);
		System.out.println(board);
	}
}













