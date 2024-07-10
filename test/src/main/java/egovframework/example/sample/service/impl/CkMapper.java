package egovframework.example.sample.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.sample.service.Newsletter;

@Mapper("ckMapper")
public interface CkMapper {
	int insNews(Newsletter ins) throws Exception;
	List<Newsletter> newsList() throws Exception; 
	Newsletter newsBynewsId(String news_id) throws Exception;
	int uptNews(Newsletter upt) throws Exception;
	int delNews(String news_id) throws Exception;
}
