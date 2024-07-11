package egovframework.example.sample.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.sample.service.FileStorage;
import egovframework.example.sample.service.Mail;

@Mapper("mailMapper")
public interface MailMapper {
	int insMail(Mail ins) throws Exception;
	int insFileWithMail(FileStorage ins) throws Exception;
	List<Mail> mailList() throws Exception;
	Mail mailDetail(String mail_id) throws Exception;
}
