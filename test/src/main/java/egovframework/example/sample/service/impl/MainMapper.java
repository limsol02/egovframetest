package egovframework.example.sample.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

// egovframework.example.sample.service.impl.MainMapper
@Mapper("mainMapper")
public interface MainMapper {
	int allBoardCount() throws Exception;
	int boardWithFile() throws Exception;
}
