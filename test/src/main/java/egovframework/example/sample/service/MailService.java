package egovframework.example.sample.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface MailService {
	int insMail(Mail ins) throws Exception;
	int insFileWithMail(FileStorage ins, Mail insMail,MultipartFile[] files) throws Exception;
	List<Mail> mailList() throws Exception;
	Mail mailDetail(String mail_id) throws Exception;
	int delMail(String mail_id) throws  Exception;

}
