package com.group.board.member.controller;

import java.io.IOException;

import com.group.board.exception.InvalidLoginException;
import com.group.board.member.dto.LoginDTO;
import com.group.board.member.service.MemberService;
import com.group.board.util.ServletUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {

	MemberService memberService = MemberService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		LoginDTO loginDTO = new LoginDTO(id, pw);
		try {
			String loginId = memberService.login(loginDTO);

			HttpSession session = req.getSession();
			session.setAttribute("member", loginId);

			ServletUtil.setRequestMsgAndPath(req, "로그인 되었습니다.", req.getContextPath());

			if (req.getParameter("remember").equals("on")) {
				Cookie cookie = new Cookie("loginId", loginId);
				cookie.setMaxAge(60 * 60 * 24 * 30);
				// cookie.setPath(req.getContextPath()+ "/login");
				resp.addCookie(cookie);
			} else {
				Cookie[] cookies = req.getCookies();
				for (Cookie cookie : cookies) {
					if (!cookie.getName().equals("loginId")) {
						continue;
					}
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
				}
			}

			req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
		} catch (InvalidLoginException e) { // 로그인 실패
			ServletUtil.setRequestMsgAndPath(req, e.getMessage(), req.getContextPath() + "/login");
			req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
		} catch (RuntimeException e) { // SQL 오류
			ServletUtil.setRequestMsgAndPath(req, e.getMessage(), req.getContextPath() + "/login");
			req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
		}
	}
}
