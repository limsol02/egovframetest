package egovframework.example.sample.service.impl;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.MainService;

@Service("mainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService{
	@Resource(name="mainMapper")
	private MainMapper dao;
	
	@Override
	public int allBoardCount() throws Exception {
		return dao.allBoardCount();
	}
	@Override
	public int boardWithFile() throws Exception {
		return dao.boardWithFile();
	}
}
