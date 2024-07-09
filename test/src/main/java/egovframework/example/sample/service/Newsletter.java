package egovframework.example.sample.service;

public class Newsletter {

	private String news_id;
	private String content;
	private String writter;
	private String reg_date;
	private String upt_date;
	private String image_path;
	private String title;
	public Newsletter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Newsletter(String content, String writter, String title) {
		super();
		this.content = content;
		this.writter = writter;
		this.title = title;
	}


	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWritter() {
		return writter;
	}
	public void setWritter(String writter) {
		this.writter = writter;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUpt_date() {
		return upt_date;
	}
	public void setUpt_date(String upt_date) {
		this.upt_date = upt_date;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
