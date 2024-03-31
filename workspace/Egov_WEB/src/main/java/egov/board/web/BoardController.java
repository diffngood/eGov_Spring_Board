package egov.board.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egov.board.service.BoardService;

@Controller
public class BoardController {
	
	@Resource(name = "BoardService")
	BoardService boardService;
	
	@RequestMapping(value = "/boardWrite.do")
	public String boardWrite(HttpServletRequest request, ModelMap model) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			boardService.checkUser(request);
			
		} catch (Exception e) {
			// 로그기록, 상태코드반환 또는 에러페이지 전달
			String error = e.getMessage();
			if(error.equals("로그인 안했음")) {
				return "redirect:/login.do";
			}
			else {
				// 일반 예외 페이지
			}
			
			System.out.println(e);
			return "error/error";
		}
		
		return "board/boardwrite";
	}
	
	@RequestMapping(value = "/boardInsert.do")
	public String boardInsert(HttpServletRequest request, ModelMap model) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			boardService.saveBoard(request);
			
		} catch (Exception e) {
			// 로그기록, 상태코드반환 또는 에러페이지 전달
			String error = e.getMessage();
			if(error.equals("로그인 안했음")) {
				return "redirect:/login.do";
			}
			else if (error.equals("제목을 다시 확인해주세요")) {
				return "redirect:/boardWrite.do";
			}
			else {
				// 일반 예외 페이지
			}
			
			System.out.println(e);
			return "error/error";
		}
		
		return "board/boardwrite";
	}
	
	@RequestMapping(value = "/boardView.do")
	public String boardView(HttpServletRequest request, ModelMap model) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			resultMap = boardService.showBoard(request);
			
		} catch (Exception e) {
			// 로그기록, 상태코드반환 또는 에러페이지 전달
			String error = e.getMessage();
			if(error.equals("로그인 안했음")) {
				return "redirect:/login.do";
			}
			else if(error.equals("유효성검사실패")) {
				// return 404페이지 또는 게시판 페이지?
			}
			else if(error.equals("페이지찾을수없음")) {
				// 게시판 num이 null인 경우
			}
			else {
				// 일반 예외 페이지
			}
			
			System.out.println(e);
			return "error/error";
		}
		
		model.addAttribute("userid", resultMap.get("userid").toString());
		model.addAttribute("title", resultMap.get("title").toString());
		model.addAttribute("boardcontents", resultMap.get("boardcontents").toString());
		
		return "board/boardview";
	}
	
	
	
	@RequestMapping(value = "/boardList.do")
	public String boardList(HttpServletRequest request, ModelMap model) {
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			
			list = boardService.showBoardList(request);
			
		} catch (Exception e) {
			// 로그기록, 상태코드반환 또는 에러페이지 전달
			String error = e.getMessage();
			if(error.equals("로그인 안했음")) {
				return "redirect:/login.do";
			}
			else if(error.equals("유효성검사실패")) {
				// return 404페이지 또는 게시판 페이지?
			}
			else if(error.equals("페이지찾을수없음")) {
				// 게시판 num이 null인 경우
			}
			else {
				// 일반 예외 페이지
			}
			
			System.out.println(e);
			return "error/error";
		}
		
		model.addAttribute("boardlist", list);
		
		return "board/boardlist2";
	}
	
}