package com.group.board.board.controller;

import java.io.IOException;

import com.group.board.board.domain.BoardDTO;
import com.group.board.board.dto.BoardWriteDTO;
import com.group.board.board.service.BoardService;
import com.group.board.util.ServletUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet {

	BoardService boardService = BoardService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인
		HttpSession session = req.getSession();
		if (session.getAttribute("member") == null) {
			ServletUtil.setRequestMsgAndPath(req, "로그인된 사용자가 아닙니다.", req.getContextPath() + "/board/list");
			req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
		}

		req.getRequestDispatcher("/WEB-INF/views/board/board-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String writer = req.getSession().getAttribute("member").toString();
		String content = req.getParameter("content");

		BoardWriteDTO boardWriteDTO = new BoardWriteDTO(title, writer, content);
		boardService.writeBoard(boardWriteDTO);
		resp.sendRedirect("/application/board/list");
	}
}
