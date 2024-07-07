package egovframework.example.sample.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="uptBoard.do", method = RequestMethod.POST)
	public ResponseEntity<?> uptBoard( BoardVO vo) throws Exception {
        int uptResult = service.uptBoard(vo);
        
        if (uptResult == 1) {
            System.out.println("ezez");
        } else {
            System.out.println("xx");
        }
        
        return ResponseEntity.ok(uptResult);
    }
	
	@RequestMapping(value="delBoard.do", method = RequestMethod.GET)
	public ResponseEntity<?> delBoard(@RequestParam("board_id")String board_id) throws Exception {
		int delResult = service.delBoard(board_id);
		if(delResult == 1) {
			 System.out.println("ezez");
		}else {
			System.out.println("xx");
		}
		
		return ResponseEntity.ok(delResult);
	}

	@RequestMapping(value="insertFrm.do", method= RequestMethod.GET)
	public String insertFrm() {
		return "test/insert";
	}
	
	@RequestMapping(value="insertBoard.do", method = RequestMethod.POST)
	public String insertBoard(BoardVO vo) throws Exception {
	    int insResult = service.insBoard(vo);
	    if(insResult > 0) {
	        System.out.println("등록성공");
	        return "redirect:/board.do";
	    } else {
	        System.out.println("등록실패");
	        return "redirect:/insertFrm.do"; // 실패 시 다시 폼으로 이동
	    }
	}
}
