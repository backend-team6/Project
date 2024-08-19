package com.group.board.board.controller;

import java.io.IOException;

import com.group.board.board.domain.BoardDTO;
import com.group.board.board.dto.UpdateDTO;
import com.group.board.board.service.BoardService;
import com.group.board.util.ServletUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
	BoardService boardService = BoardService.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int no = Integer.parseInt(req.getParameter("no"));
		HttpSession session = req.getSession();
		BoardDTO board = boardService.getBoardInfo(no);
		
		// 권한 확인
		if (session.getAttribute("member") == null) {
			ServletUtil.setRequestMsgAndPath(req, "로그인된 사용자가 아닙니다.", req.getContextPath() + "/board/list");
			req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
		}
		else if (!session.getAttribute("member").toString().equals(board.getWriter())) {
			ServletUtil.setRequestMsgAndPath(req, "작성자와 일치하지 않습니다.", req.getContextPath() + "/board/list");
			req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
		}
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board/update-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int no = Integer.parseInt(req.getParameter("no"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		System.out.println("title = " + title);
		System.out.println("content = " + content);

		UpdateDTO updateDTO = new UpdateDTO(no, title, content);
		boardService.update(updateDTO);
		resp.sendRedirect("/application/board/list");
	}
}
