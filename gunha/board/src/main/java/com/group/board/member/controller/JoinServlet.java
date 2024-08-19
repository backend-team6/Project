package com.group.board.member.controller;

import java.io.IOException;

import com.group.board.exception.DuplicateLoginIdException;
import com.group.board.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/join")
public class JoinServlet extends HttpServlet {

	MemberService memberService = MemberService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String password = req.getParameter("pw");

		try {
			memberService.join(id, password);
			req.setAttribute("msg", "회원가입이 완료되었습니다.");
			req.setAttribute("path", req.getContextPath());
			req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
		} catch (DuplicateLoginIdException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("path", req.getContextPath() + "/join");
			req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
		} catch (RuntimeException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("path", req.getContextPath() + "/join");
			req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
		}
	}
}
