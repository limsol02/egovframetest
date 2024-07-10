package egovframework.example.sample.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.sample.service.Mail;

@Mapper("mailMapper")
public interface MailMapper {
	int insMail(Mail ins) throws Exception;
	
}
