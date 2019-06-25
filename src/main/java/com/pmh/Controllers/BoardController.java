package com.pmh.Controllers;

import java.util.List;

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
		System.out.println(title);
		System.out.println(content);
		String now_username = APcal.getUsername();

		Board board = new Board();
		board.setContent(content);
		board.setTitle(title);
		board.setUsername(now_username);
		bservice.inputContent(board);
		return "redirect:boardList";
	}

	@RequestMapping("/boardList") // 게시글 리스트
	public void boardView(Model model,
			@PageableDefault(sort = { "bno" }, direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		Page<Board> boardPage = bservice.findAll(pageable);

		model.addAttribute("boardPage", boardPage);

	}

	@RequestMapping("BView") // 게시글 페이지
	public void BView(int bno, Model model) {
		Board boardCon = bservice.findByBno(bno);
		List<BoardComment> boardComment = BCservice.findByBno(bno);

		model.addAttribute("boardCon", boardCon);
		model.addAttribute("boardComment", boardComment);
	}

	@RequestMapping("BDel") // 게시글 삭제
	public String Bdel(int bno) {
		bservice.BDel(bno);
		return "redirect:boardList";
	}

	@RequestMapping("BUpdateView") // 게시글 업데이트 페이지
	public void BUpViewGet(int bno, Model model) {
		Board updateCon = bservice.findByBno(bno);
		model.addAttribute("updateCon", updateCon);
	}

	@RequestMapping("BUpdate") // 게시글 업데이트
	public String BUpdate(Board board) {
		board.getBno();
		board.getTitle();
		board.getContent();
		board.getUsername();
		bservice.inputContent(board);

		return "redirect:boardList";
	}

	/*---------------댓글----------------------*/
	@RequestMapping("/commentList") // 댓글 리스트
	public void BCommentList(Model model) {
		List<BoardComment> bcComment = BCservice.commentList();
		model.addAttribute("commentList", bcComment);
	}

	@RequestMapping("/addComment") // 댓글 삽입
	public String addComment(@AuthenticationPrincipal AccountPrincipal APcal, int bno, String bccomment) {
		String bcwriter = APcal.getUsername();

		BoardComment boardComment = new BoardComment();
		boardComment.setBno(bno);
		boardComment.setBccomment(bccomment);
		boardComment.setBcwriter(bcwriter);

		BCservice.commentInsert(boardComment);
		return "redirect:boardList";
	}

	@RequestMapping("/updateComment") // 댓글 수정
	public void updateComment(@AuthenticationPrincipal AccountPrincipal APcal, int cno, String bccomment,
			String bcwriter) {
		BoardComment boardComment = new BoardComment();
		if (APcal.getUsername() == bcwriter) {
			boardComment.setBccomment(bccomment);
			boardComment.setCno(cno);
			BCservice.findByCno(boardComment);
		} else {

		}

	}

	@RequestMapping("/deleteComment") // 댓글 수정
	public void deleteComment(@AuthenticationPrincipal AccountPrincipal APcal, int cno, String bccomment,
			String bcwriter) {
		BoardComment boardComment = new BoardComment();
		if (APcal.getUsername() == bcwriter) {

			boardComment.setBccomment(bccomment);
			BCservice.Cdel(cno);
		} else {

		}

	}
}
