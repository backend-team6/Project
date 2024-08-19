package com.yeonsu.controller;

import com.yeonsu.model.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equals(action)) { //로그인
            HttpSession session = req.getSession();
            String loginId = (String) session.getAttribute("loginid");
            if (loginId == null) {
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            } else {
                req.setAttribute("msg", "이미 로그인 내역이 있습니다.");
                req.setAttribute("path", req.getContextPath()); //맨 첫페이지로 보냄
                req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
            }
        } else if ("logout".equals(action)) { //로그아웃
            HttpSession session = req.getSession();
            session.invalidate(); //removeAttribute("loginId") 해도 되지만 보통 지금 로그아웃하는 사용자의 정보 싹 날리느라 그냥 세션객체 자체를 없애버림
            req.setAttribute("msg", "로그아웃 되었습니다");
            req.setAttribute("path", req.getContextPath()); //맨 첫페이지로 보냄
            req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
        } else if ("singup".equals(action)) { //회원가입
            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String loginId = req.getParameter("loginId");
        String loginPw = req.getParameter("loginPw");
        String check = req.getParameter("check");

        try {
            if ("login".equals(action)) { //로그인
                if (userService.login(loginId, loginPw)) {

                    Cookie[] cookies = req.getCookies();
                    if (check != null) { //아이디 기억하기 눌렀을 떄 -- 쿠키 발급
                        System.out.println("체크");

                        Cookie cookie = new Cookie("cookie" + loginId, loginId); // 쿠키이름, 쿠키값 (쿠키는 key와 value를 가짐)
                        cookie.setMaxAge(60 * 5); // 쿠키 유효시간 설정
                        resp.addCookie(cookie);

                    } else { //아이디 기억하기 체크 삭제했을 때 -- 쿠키 삭제
                        System.out.println("체크X");
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("cookie" + loginId)) {
                                    // 해당 이름의 쿠키를 찾으면 삭제
                                    cookie.setMaxAge(0); // 쿠키를 즉시 만료시킴
                                    resp.addCookie(cookie); // 응답에 추가하여 삭제 처리
                                    break;
                                }
                            }
                        }
                    }

                    //login success
                    HttpSession session = req.getSession();
                    session.setAttribute("loginId", loginId);
                    req.setAttribute("msg", "로그인 완료되었습니다.");
                    req.setAttribute("path", req.getContextPath()); //맨 첫페이지로 보냄
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                } else {
                    //login fail
                    req.setAttribute("msg", "로그인 실패입니다. 아이디나 패스워드를 확인해주세요.");
                    req.setAttribute("path", req.getContextPath() + "/user?action=login"); //로그인 페이지로 보냄
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                }
            } else if ("signup".equals(action)) { //회원가입
                if (userService.signup(loginId, loginPw)) {
//                HttpSession session = req.getSession();
//                session.setAttribute("loginId", loginId);
                    req.setAttribute("msg", "회원가입 완료되었습니다.");
                    req.setAttribute("path", req.getContextPath()); //맨 첫페이지로 보냄
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                } else {
                    req.setAttribute("msg", "회원가입 실패입니다. ");
                    req.setAttribute("path", req.getContextPath() + "/user?action=signup"); //회원가입 페이지로 보냄
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
