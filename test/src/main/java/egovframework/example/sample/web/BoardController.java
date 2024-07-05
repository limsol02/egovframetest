package egovframework.example.sample.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.sample.service.BoardService;
import egovframework.example.sample.service.BoardVO;

@Controller
public class BoardController {
	
	@Resource(name="boardService")
	private BoardService service;

	@RequestMapping(value="main.do", method= RequestMethod.GET)
	public String main() {
		return "test/main";
	}
	
	@RequestMapping(value="board.do")
	public String board(Model d) throws Exception {
		List<BoardVO> board = service.selectBoard();
		d.addAttribute("blist",board);
		return "test/board";
	}
	
	@RequestMapping(value="boardDetail.do",method = RequestMethod.GET)
	public String boardDetail(@RequestParam("board_id")String board_id,Model d) throws Exception {
		d.addAttribute("detail",service.selectBoardById(board_id));
		return "test/detail";
	}
	
	
	
}
