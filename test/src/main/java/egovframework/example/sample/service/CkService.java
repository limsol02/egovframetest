package egovframework.example.sample.service;

import java.util.List;

public interface CkService {
	int insNews(Newsletter ins) throws Exception;
	List<Newsletter> newsList() throws Exception;
	Newsletter newsBynewsId(String news_id) throws Exception;
}
