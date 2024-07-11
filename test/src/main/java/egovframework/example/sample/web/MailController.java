package egovframework.example.sample.web;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.sample.service.FileStorage;
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
	    public void mailSending(Mail ins, HttpServletResponse res, MultipartFile[] files) {
	        String setfrom = "ghdwjdgh89@gmail.com"; // 본인의 이메일 주소
	        String tomail = ins.getReceiver(); // 받는 사람 이메일
	        String title = ins.getTitle(); // 메일 제목
	        String content = ins.getContent(); // 메일 내용
	        System.out.println(content);

	        try {
	            res.setContentType("text/html;charset=UTF-8");
	            PrintWriter writer = res.getWriter();
	            // HTML 태그 제거 및 평문 텍스트 추출
	            String plainTextContent = Jsoup.parse(content).text();
	            
	            // 메일 발송
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

	            messageHelper.setFrom(setfrom, "임솔");
	            messageHelper.setTo(tomail);
	            messageHelper.setSubject(title != null ? title : ""); // null 검사
	            messageHelper.setText(plainTextContent, false); // HTML태그를 무시하고 평문으로 전송
	            
	            // 파일 첨부
	            if (files != null && files.length > 0) {
	                for (MultipartFile file : files) {
	                    messageHelper.addAttachment(file.getOriginalFilename(), file);
	                }
	            }
	            
	            mailSender.send(message);
	            writer.print("메일 발송 완료");

	            // 파일 및 메일 등록 서비스 호출
	            service.insFileWithMail(new FileStorage(), ins, files);
	        } catch (Exception e) {
	            System.out.println("메일 발송 중 오류 발생: " + e.getMessage());
	            e.printStackTrace(); // 스택 트레이스를 출력하여 디버깅에 도움을 줌

	            // 스택 트레이스를 로깅하는 코드
	            for (StackTraceElement element : e.getStackTrace()) {
	                System.out.println("at " + element.getClassName() + "." + element.getMethodName() + "(" + element.getFileName() + ":" + element.getLineNumber() + ")");
	            }
	        }
	    }
	
	  @RequestMapping(value="mailList.do", method = RequestMethod.GET)
	  public String mailList(Model d) throws Exception {
		  d.addAttribute("mlist",service.mailList());
		  return "test/mail";
	  }
	  
	  @RequestMapping(value="mailDetail.do", method = RequestMethod.GET)
	  public String mailDetail (Model d,@RequestParam("mail_id")String mail_id) throws Exception {
		  d.addAttribute("detail",service.mailDetail(mail_id));
		  return "test/mailDetail";
	  }
	  
	  @RequestMapping(value="delMail.do", method = RequestMethod.GET)
	  public ResponseEntity<?> delMail(@RequestParam("mail_id")String mail_id) throws Exception{
		  
		  try {
			  return ResponseEntity.ok(service.delMail(mail_id));
		  }catch (Exception e) {
			  System.out.println(e.getMessage());
			  return ResponseEntity.ok(e.getMessage());
		  }
		  
	  }  
}
