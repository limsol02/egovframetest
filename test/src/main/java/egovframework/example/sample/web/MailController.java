package egovframework.example.sample.web;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.sample.service.Mail;
import egovframework.example.sample.service.MailService;

@Controller
public class MailController {
	
	@Resource(name="mailSender")
	private JavaMailSender mailSender;
	@Resource(name="mailService")
	private MailService service;
	
	@RequestMapping(value="mail.do",method = RequestMethod.GET)
	public String mail () {
		return "test/sendMail";
	}
	
	 @RequestMapping(value = "sendMail.do", method = RequestMethod.POST)
	    @ResponseBody
	    public void mailSending(Mail ins, HttpServletResponse res) {
	        //System.out.println(email);
	        String setfrom = "ghdwjdgh89@gmail.com"; // 본인의 이메일 주소

	        String tomail = ins.getReceiver(); // 받는 사람 이메일
	        String title = ins.getTitle();
	        String content = ins.getContent();

	        try {
	            res.setContentType("text/html;charset=UTF-8");
	            PrintWriter writer = res.getWriter();

	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

	            messageHelper.setFrom(setfrom, "임솔");
	            messageHelper.setTo(tomail);
	            messageHelper.setSubject(title);
	            messageHelper.setText(content);

	            mailSender.send(message);

	            writer.print("메일 발송 완료");
	        } catch (Exception e) {
	            System.out.println(e);
	            System.out.println(e.getMessage());
	        }
	    }
	 
	
}
