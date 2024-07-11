package egovframework.example.sample.service;

public class Mail {
	private String mail_id;
	private String receiver;
	private String title;
	private String content;
	private String send_date;
	private FileStorage filestorage;
	
	public Mail() {
	}
	public Mail(String mail_id, String receiver, String title, String content, String send_date) {
		super();
		this.mail_id = mail_id;
		this.receiver = receiver;
		this.title = title;
		this.content = content;
		this.send_date = send_date;
	}
	public String getMail_id() {
		return mail_id;
	}
	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	
	public FileStorage getFilestorage() {
		return filestorage;
	}
	public void setFilestorage(FileStorage filestorage) {
		this.filestorage = filestorage;
	}
	
}
