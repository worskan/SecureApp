package com.pmh.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmh.Domains.Board;
import com.pmh.Service.BoardService;
import com.pmh.secureapp.AccountPrincipal;

@Controller
public class BoardController {

	@Autowired
	private BoardService bservice;

	@RequestMapping("/boardInputView")
	public void boardInputView() {

	}

	@PostMapping("/boardInput")
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

	@RequestMapping("/boardList")
	public void boardView(Model model) {
		List<Board> board = bservice.allContent();
		model.addAttribute("board", board);

	}
}
