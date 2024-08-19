package com.group.board.board.controller;

import java.io.IOException;

import com.group.board.board.domain.BoardDTO;
import com.group.board.board.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/info")
public class BoardInfoServlet extends HttpServlet {

	BoardService boardService = BoardService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int no = Integer.parseInt(req.getParameter("no"));
		String loginId = req.getSession().getAttribute("member").toString();

		BoardDTO boardInfo = boardService.getBoardInfo(no);
		if (!boardInfo.getWriter().equals(loginId)) {
			boardService.incrementReadCount(no);
		}
		req.setAttribute("board", boardInfo);
		req.getRequestDispatcher("/WEB-INF/views/board/board-info.jsp").forward(req, resp);
	}
}
