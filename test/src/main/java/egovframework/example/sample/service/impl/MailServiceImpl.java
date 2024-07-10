package egovframework.example.sample.service.impl;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.Mail;
import egovframework.example.sample.service.MailService;

@Service("mailService")
public class MailServiceImpl extends EgovAbstractServiceImpl implements MailService{
	
	@Resource(name="mailMapper")
	private MailMapper dao;
	
	@Override
	public int insMail(Mail ins) throws Exception {
		return dao.insMail(ins);
	}
	
}
