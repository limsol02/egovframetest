package egovframework.example.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.BoardService;
import egovframework.example.sample.service.BoardVO;

@Service("boardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService {

    @Resource(name="boardMapper")
    private BoardMapper dao;
    
    @Resource(name="boardService")
    private BoardService service;

    @Override
    public List<BoardVO> selectBoard() throws Exception {
        return dao.selectBoard();
    }
    @Override
    public BoardVO selectBoardById(String board_id) throws Exception {
    	return dao.selectBoardById(board_id);
    }
    @Override
    public int uptBoard(BoardVO vo) throws Exception {
    	return dao.uptBoard(vo);
    }
   
}
