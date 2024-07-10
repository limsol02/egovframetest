package egovframework.example.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.CkService;
import egovframework.example.sample.service.Newsletter;

@Service("ckService")
public class CkServiceImpl extends EgovAbstractServiceImpl implements CkService{
	
	@Resource(name="ckMapper")
	private CkMapper dao;
	
	@Override
	public int insNews(Newsletter ins) throws Exception {
		return dao.insNews(ins);
	}
	@Override
	public List<Newsletter> newsList() throws Exception {
		return dao.newsList();
	}
	@Override
	public Newsletter newsBynewsId(String news_id) throws Exception {
		return dao.newsBynewsId(news_id);
	}
	@Override
	public int uptNews(Newsletter upt) throws Exception {
		return dao.uptNews(upt);
	}
	@Override
	public int delNews(String news_id) throws Exception {
		return dao.delNews(news_id);
	}
}
