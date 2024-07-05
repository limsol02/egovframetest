package egovframework.example.sample.service;

import java.util.List;

public interface BoardService {
    List<BoardVO> selectBoard() throws Exception;
    BoardVO selectBoardById(String board_id) throws Exception;
    int uptBoard(BoardVO vo) throws Exception;
}
