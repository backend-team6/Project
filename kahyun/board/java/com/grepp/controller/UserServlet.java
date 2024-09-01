package com.grepp.controller;

import com.grepp.model.UserDTO;
import com.grepp.model.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
    UserService service=UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");

        try{
            if("login".equals(action)){
                HttpSession session=req.getSession();
                String loginId=(String)session.getAttribute("loginId");
                if(loginId==null) {
                    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                }else{
                    req.setAttribute("msg", "이미 로그인 내역이 있습니다");
                    req.setAttribute("path","main.do");
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req,resp);
                }
            }else if("signUp".equals(action)){
                req.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(req,resp);
            }else if("logout".equals(action)){
                HttpSession session=req.getSession();
                session.invalidate();
                req.setAttribute("msg", "로그아웃 되었습니다");
                req.setAttribute("path",req.getContextPath()); //메인으로 보내기
                req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req,resp);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");

        try{
            if("login".equals(action)){
                String id=req.getParameter("id");
                String password=req.getParameter("password");
                UserDTO user=new UserDTO(id,password);
                int result=service.login(user);
                if(result==1){
                    HttpSession session=req.getSession();
                    session.setAttribute("loginId",user.getId());

                    String idSave=req.getParameter("idSave");
                    if(idSave!=null){
                        //쿠키 발행
                        Cookie cookie=new Cookie("userId",user.getId());
                        cookie.setMaxAge(60*60);
                        resp.addCookie(cookie);
                        Cookie chkCookie=new Cookie("checked","기억하기");
                        chkCookie.setMaxAge(60*60);
                        resp.addCookie(chkCookie);
                    }else{// id 기억하기 취소하는 경우
                        Cookie chkCookie=new Cookie("checked","기억하지않기");
                        chkCookie.setMaxAge(60*60);
                        resp.addCookie(chkCookie);
                        Cookie cookie=new Cookie("userId","");
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    }

                    req.setAttribute("msg", "로그인 성공");
                    req.setAttribute("path",req.getContextPath()+"/board.do?action=list");
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req,resp);
                }else{
                    req.setAttribute("msg", "로그인 실패");
                    req.setAttribute("path",req.getContextPath());
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req,resp);
                }
            }else if("signUp".equals(action)){
                String name=req.getParameter("name");
                String id=req.getParameter("id");
                String password=req.getParameter("password");
                UserDTO user=new UserDTO(name, id,password);
                int result=service.signUp(user);
                if(result==1){
                    req.setAttribute("msg", "회원가입 성공");
                    req.setAttribute("path",req.getContextPath());
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req,resp);
                }else{
                    req.setAttribute("msg", "회원가입 실패");
                    req.setAttribute("path",req.getContextPath());
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req,resp);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
