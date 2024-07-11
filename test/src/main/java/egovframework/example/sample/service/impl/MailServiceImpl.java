package egovframework.example.sample.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.sample.service.FileStorage;
import egovframework.example.sample.service.Mail;
import egovframework.example.sample.service.MailService;

@Service("mailService")
public class MailServiceImpl extends EgovAbstractServiceImpl implements MailService{
	
	@Resource(name="mailMapper")
	private MailMapper dao;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Override
	public int insMail(Mail ins) throws Exception {
		return dao.insMail(ins);
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Override
    public int insFileWithMail(FileStorage insFile, Mail insMail, MultipartFile[] files) throws Exception {
        int result = 0;
        
        try {
            // 메일 DB 등록
            int mailResult = dao.insMail(insMail);
            if (mailResult > 0) {
                System.out.println("메일 등록 성공");
            } else {
                System.out.println("메일 등록 실패");
                throw new Exception("메일 등록 실패");
            }

            // 파일 등록 (파일이 있을 경우에만 처리)
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                	// 파일 원래이름 가져오기(안하니까 이상하게 가져옴)
                    String fileName = file.getOriginalFilename();
                    // Properties 경로 참조
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs(); // 업로드 디렉토리가 없으면 생성
                    }
                    File uploadFile = new File(uploadDir, fileName);
                    file.transferTo(uploadFile);
                    // 파일 DB 저장
                    FileStorage fs = new FileStorage(fileName, uploadPath);
                    int fileResult = dao.insFileWithMail(fs);
                    if (fileResult <= 0) {
                        throw new Exception("파일 등록 실패");
                    }
                }
            }

            result = 1; 
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
            throw e; // 예외 발생 시 트랜잭션 롤백
        }

        return result;
    }
	
	@Override
	public List<Mail> mailList() throws Exception {
		return dao.mailList();
	}
	
	@Override
	public Mail mailDetail(String mail_id) throws Exception {
		return dao.mailDetail(mail_id);
	}
	
}
