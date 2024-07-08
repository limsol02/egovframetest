package egovframework.example.sample.service;

public class FileStorage {
	private String file_id;
	private String fname;
	private String path;
	private String board_id;
	
	
	
	public FileStorage(String fname, String path) {
		super();
		this.fname = fname;
		this.path = path;
	}
	public FileStorage() {
		// TODO Auto-generated constructor stub
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	
	
}
