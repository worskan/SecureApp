package com.pmh.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmh.Domains.Board;
import com.pmh.Domains.BoardComment;
import com.pmh.Repositories.BoardCommentRepository;
import com.pmh.Service.BoardCommentService;
import com.pmh.Service.BoardService;
import com.pmh.secureapp.AccountPrincipal;

@Controller
public class BoardController {

	@Autowired
	private BoardService bservice;

	@Autowired
	private BoardCommentService BCservice;

	@RequestMapping("/boardInputView")
	public void boardInputView() {

	}

	@PostMapping("/boardInput") // 게시글 삽입
	public String boardInput(String title, String content, @AuthenticationPrincipal AccountPrincipal APcal) {
		System.out.println("작성자 "+APcal.getUsername());
		System.out.println("제목 "+title);
		System.out.println("내용 "+content);
		String now_username = APcal.getUsername();

		Board board = new Board();
		board.setContent(content);
		board.setTitle(title);
		board.setUsername(now_username);
		bservice.inputContent(board);
		return "redirect:boardList";
	}

	@RequestMapping("/boardList") // 게시글 리스트
	public void boardView(Model model,  @AuthenticationPrincipal AccountPrincipal APcal,
			@PageableDefault(sort = { "bno" }, direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		Page<Board> boardPage = bservice.findAll(pageable);

		String now_username=APcal.getUsername();
		model.addAttribute("now_username", now_username);
		model.addAttribute("boardPage", boardPage);

	}

	@RequestMapping("BView") // 게시글 페이지
	public void BView(int bno, Model model) {
		Board boardCon = bservice.findByBno(bno);
		List<BoardComment> boardComment = BCservice.findByBno(bno);

		model.addAttribute("boardCon", boardCon);
		model.addAttribute("boardComment", boardComment);
	}

	
	@RequestMapping("BDel") // 게시글 삭제 페이지
	public String BDel(int bno,String username, Model model, @AuthenticationPrincipal AccountPrincipal APcal) {
		 String data ="false";
		 System.out.println(username+","+APcal.getUsername());
		if(APcal.getUsername().equals(username)) {
			
			bservice.BDel(bno);
			return "boardList";
		}else {
		return data;
		}
	}
	

	
	@ResponseBody
	@RequestMapping("BUpdatePrc") // 게시글 수정 페이지
	public String BUpViewGet(int bno,String username, Model model, @AuthenticationPrincipal AccountPrincipal APcal) {
		if(APcal.getUsername().equals(username)) {
			return "BUpdateView?bno="+bno;
		}
		return "false";
	}
	
	@RequestMapping("BUpdateView")
	public void BUpdateView(String username, int bno,Model model,HttpServletRequest request) {
		String refere = request.getHeader("referer");
		Board updateCon = bservice.findByBno(bno);
		model.addAttribute("refere", refere); //이전 페이지 url
		model.addAttribute("updateCon", updateCon);
	}

	@RequestMapping("BUpdate") // 게시글 수정
	public String BUpdate(int bno, String content, String title, String username,@AuthenticationPrincipal AccountPrincipal APcal) {
	
		Board board = new Board();
		
		if(APcal.getUsername().equals(username)) {
			board.setBno(bno);
			board.setContent(content);
			board.setTitle(title);
			board.setUsername(username);
			bservice.inputContent(board);
			return "redirect:boardList";
		}
		else {
			return "redirect:boardList";
		}
		
	}

	/*---------------댓글----------------------*/
	@RequestMapping("/commentList") // 댓글 리스트
	public void BCommentList(Model model) {
		List<BoardComment> bcComment = BCservice.commentList();
		model.addAttribute("commentList", bcComment);
	}

	@ResponseBody
	@RequestMapping("/addComment") // 댓글 삽입
	public String addComment(@AuthenticationPrincipal AccountPrincipal APcal, int bno, String bccomment) {
		String bcwriter = APcal.getUsername();
		BoardComment boardComment = new BoardComment();
		boardComment.setBno(bno);
		boardComment.setBccomment(bccomment);
		boardComment.setBcwriter(bcwriter);

		BCservice.commentInsert(boardComment);

		return "BView?bno="+bno;
	}
	
	@ResponseBody
	@RequestMapping("/deleteComment") // 댓글 삭제
	public String deleteComment(@AuthenticationPrincipal AccountPrincipal APcal, int cno, int bno, String bcwriter) {
		String data;
		
		if (APcal.getUsername().equals(bcwriter)) { // ==(동등비교) 대신 equals로 처리
			BCservice.Cdel(cno);
			System.out.println("-----------------------");
			System.out.println(cno+"번째 댓글이 삭제되었습니다.");
			System.out.println("-----------------------");
			return "BView?bno="+bno;
		} else {
			data="false";
			System.out.println("-----------------------");
			System.out.println(cno+"번째 댓글을"+bcwriter+"이(가) 삭제할 수 없습니다.");
			System.out.println("-----------------------");
			return data;
		}
		
	}
	
	@RequestMapping("/commentUpdateView") // 댓글 수정 페이지
	public void commentUpdateView(int cno, Model model, HttpServletRequest request, @AuthenticationPrincipal AccountPrincipal APcal) {
		String refere = request.getHeader("referer");
		BoardComment boardComment = BCservice.findByCno(cno);
		
		model.addAttribute("refere", refere); //이전 페이지 url
		model.addAttribute("boardComment", boardComment);		
	}

	@ResponseBody
	@RequestMapping("/BCUpdate") // 댓글 접근 처리
	public String BCUpdate(int bno, int cno,String bcwriter, @AuthenticationPrincipal AccountPrincipal APcal, Model model) {
		
		BoardComment boardComment = BCservice.findByCno(cno);
		System.out.println(boardComment.getBccomment());
		if(APcal.getUsername().equals(bcwriter)) {
			
			BoardComment boardComment1 = new BoardComment();
			boardComment1.setBccomment(boardComment.getBccomment());
			boardComment1.setBcwriter(APcal.getUsername());
			boardComment1.setBno(boardComment.getBno());
			boardComment1.setCno(boardComment.getCno());
			BCservice.commentInsert(boardComment);
			return "commentUpdateView?cno="+cno;
		}
		else {
			return "false";
		}
	}
	
	@RequestMapping("/BCUpdate2")
	public String BCUpdate2(int bno, int cno,String bcwriter,String bccomment, @AuthenticationPrincipal AccountPrincipal APcal, Model model) {
		
		if(APcal.getUsername().equals(bcwriter)) {
			BoardComment boardComment = new BoardComment();
		
			boardComment.setBccomment(bccomment);
			boardComment.setBcwriter(APcal.getUsername());
			boardComment.setBno(bno);
			boardComment.setCno(cno);
			BCservice.commentInsert(boardComment);
			
			model.addAttribute("bno", bno);
			
		}return "redirect:BView?bno="+bno;
	}
	
}
