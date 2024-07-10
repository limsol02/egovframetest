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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
	
	@Resource(name="mailSender")
	private JavaMailSender mailSender;
	
	 @RequestMapping(value = "emailCheck.do", method = {RequestMethod.POST, RequestMethod.GET})
	    @ResponseBody
	    public void mailSending( HttpServletResponse res) {
	        //System.out.println(email);
	        String setfrom = "ghdwjdgh89@gmail.com"; // 본인의 이메일 주소

	        String tomail = "yesshol@naver.com"; // 받는 사람 이메일
	        String title = "꿈꾸는 책다방 서비스 이메일 인증입니다.";
	        String content = "테스트 메일";

	        try {
	            res.setContentType("text/html;charset=UTF-8");
	            PrintWriter writer = res.getWriter();

	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

	            messageHelper.setFrom(setfrom, "꿈꾸는 책다방");
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
