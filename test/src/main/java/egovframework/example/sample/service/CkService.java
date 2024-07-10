package egovframework.example.sample.service;

import java.util.List;

public interface CkService {
	int insNews(Newsletter ins) throws Exception;
	List<Newsletter> newsList() throws Exception;
	Newsletter newsBynewsId(String news_id) throws Exception;
	int uptNews(Newsletter upt) throws Exception;
	int delNews(String news_id) throws Exception;
}
