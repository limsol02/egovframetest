package egovframework.example.sample.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.example.sample.service.BoardService;
import egovframework.example.sample.service.BoardVO;
import egovframework.example.sample.service.FileStorage;

@Controller
public class BoardController {

	@Resource(name = "boardService")
	private BoardService service;

	@Value("${file.upload.path}")
	private String uploadPath;


	@RequestMapping(value = "main.do", method = RequestMethod.GET)
	public String main() {
		return "test/main";
	}
	
	
	@RequestMapping(value = "board.do")
	public String board(Model d) throws Exception {
		List<BoardVO> board = service.selectBoard();
		d.addAttribute("blist", board);
		return "test/board";
	}

	@RequestMapping(value = "boardDetail.do", method = RequestMethod.GET)
	public String boardDetail(@RequestParam("board_id") String board_id, Model d) throws Exception {
		d.addAttribute("detail", service.selectBoardById(board_id));
		d.addAttribute("file",service.fileWithboard(board_id));
		return "test/detail";
	}

	@RequestMapping(value = "uptBoard.do", method = RequestMethod.POST)
	public ResponseEntity<?> uptBoard(BoardVO vo) throws Exception {
		int uptResult = service.uptBoard(vo);

		if (uptResult == 1) {
			System.out.println("ezez");
		} else {
			System.out.println("xx");
		}

		return ResponseEntity.ok(uptResult);
	}

	@RequestMapping(value = "delBoard.do", method = RequestMethod.GET)
	public ResponseEntity<?> delBoard(@RequestParam("board_id") String board_id) throws Exception {
		int delResult = service.delBoard(board_id);
		if (delResult == 1) {
			System.out.println("ezez");
		} else {
			System.out.println("xx");
		}

		return ResponseEntity.ok(delResult);
	}

	@RequestMapping(value = "insertFrm.do", method = RequestMethod.GET)
	public String insertFrm() {
		return "test/insert";
	}

	@RequestMapping(value = "insertBoard.do", method = RequestMethod.POST)
	 public String insertBoardWithFiles(BoardVO vo, MultipartFile[] files, RedirectAttributes redirectAttributes)
	            throws Exception {

	        // 먼저 게시글을 등록합니다.
	        int insResult = service.insBoard(vo);
	        if (insResult <= 0) {
	            redirectAttributes.addFlashAttribute("message", "게시글 등록 실패");
	            return "redirect:/insertFrm.do";
	        }

	        // 파일을 저장
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                try {
	                    String fileName = file.getOriginalFilename();
	                    File uploadDir = new File(uploadPath);
	                    if (!uploadDir.exists()) {
	                        uploadDir.mkdirs();
	                    }
	                    File uploadedFile = new File(uploadDir, fileName);
	                    file.transferTo(uploadedFile);

	                    // 파일 정보를 DB에 저장
	                    FileStorage fs = new FileStorage(fileName,uploadPath);
	                    service.insFile(fs);

	                } catch (IOException e) {
	                    e.printStackTrace();
	                    redirectAttributes.addFlashAttribute("message", "파일 업로드 실패: " + e.getMessage());
	                    return "redirect:/insertFrm.do";
	                }
	            }
	        }

	        redirectAttributes.addFlashAttribute("message", "등록 성공");
	        return "redirect:/board.do";
	    }

	@RequestMapping(value = "download.do", method = RequestMethod.GET)
	public void downloadFile(String fileName, HttpServletResponse response) {
		try {
			File file = new File(uploadPath + fileName);
			if (file.exists()) {
				String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
				try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
					byte[] buffer = new byte[4096];
					int bytesRead;
					while ((bytesRead = fis.read(buffer)) != -1) {
						os.write(buffer, 0, bytesRead);
					}
					os.flush();
				}
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
