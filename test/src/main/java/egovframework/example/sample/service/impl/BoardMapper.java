package egovframework.example.sample.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.sample.service.BoardVO;
import egovframework.example.sample.service.FileStorage;

@Mapper("boardMapper")
public interface BoardMapper {
    List<BoardVO> selectBoard() throws Exception;
    BoardVO selectBoardById(String board_id) throws Exception;
    int uptBoard(BoardVO vo) throws Exception;
    int insBoard(BoardVO vo) throws Exception;
    int delBoard(String board_id) throws Exception;
    int insFile(FileStorage file) throws Exception;
    FileStorage fileWithboard(String board_id) throws Exception;
}
