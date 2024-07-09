package egovframework.example.sample.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

import egovframework.example.sample.service.CkService;
import egovframework.example.sample.service.Newsletter;

@Controller
public class CkController {
	
	@Resource(name="ckService")
	private CkService service;

	/*
	 * @Value("${img.upload.path}") private String uploadPath;
	 */
    // 뉴스레터 리스트 조회
	@RequestMapping(value="newsList.do", method = RequestMethod.GET)
	public String newsList(Model d) throws Exception {
		d.addAttribute("nlist",service.newsList());
		return "test/newsletter";
	}
	
	// 상세보기
	@RequestMapping(value="news.do", method = RequestMethod.GET)
	public String news(@RequestParam("news_id") String news_id, Model d) throws Exception {
	   try {
		   System.out.println("news_id: " + news_id);
		    Newsletter news = service.newsBynewsId(news_id);
		    System.out.println("news: " + news);
		    d.addAttribute("news", news);
	   }catch (Exception e) {
		   System.out.println("에러이륜"+e.getMessage());
	   }
	    return "test/newsletterDetail";
	}
	
	// 등록form
	@RequestMapping(value="ckEditor.do", method = RequestMethod.GET)
    public String ckEditor() {
        return "test/ckeditor";
    }
	
	// 이미지 첨부 => 에디터 url 매핑
    @ResponseBody
    @RequestMapping(value = "imageUpload.do", method = RequestMethod.POST)
    public void communityImageUpload(HttpServletRequest req, HttpServletResponse resp, MultipartHttpServletRequest multiFile) throws Exception{
        JsonObject jsonObject = new JsonObject();
        PrintWriter printWriter = null;
        OutputStream out = null;
        MultipartFile file = multiFile.getFile("upload");
        
        if(file != null) {
            if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
                if(file.getContentType().toLowerCase().startsWith("image/")) {
                    try{
                        String fileName = file.getOriginalFilename();
                        byte[] bytes = file.getBytes();
                       
                        String realPath = req.getSession().getServletContext().getRealPath("/");
                        String uploadPath = realPath + "images/img/";
                        System.out.println("uploadPath: " + uploadPath);

                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs(); // 하위 디렉토리까지 생성
                        }

                        String fileName2 = UUID.randomUUID().toString();
                        String fullPath = uploadPath + fileName2 + "_" + fileName;

                        out = new FileOutputStream(new File(fullPath));
                        out.write(bytes);

                        printWriter = resp.getWriter();
                        String fileUrl = req.getContextPath() + "/images/img/" + fileName2 + "_" + fileName;
                        System.out.println("fileUrl: " + fileUrl);

                        jsonObject.addProperty("uploaded", 1);
                        jsonObject.addProperty("fileName", fileName);
                        jsonObject.addProperty("url", fileUrl);
                        printWriter.print(jsonObject);
                        System.out.println(jsonObject);
             
                    } catch(IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                        if (printWriter != null) {
                            printWriter.close();
                        }
                    }
                }
            }
        }
    }
    
    // 뉴스레터 등록
    @RequestMapping(value="insNews.do", method = RequestMethod.POST)
    public String insNews(Newsletter ins) {
        Newsletter n = new Newsletter(ins.getContent(), ins.getWritter(), ins.getTitle());
        int insResult = 0;
        
        try {
            insResult = service.insNews(n);
            return "redirect:/newsList.do";
        } catch (Exception e) {
            // 예외 메시지 출력 및 로그 기록
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "redirect:/newsList.do";
        }
    }
}