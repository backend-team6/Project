package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.model.dto.MemberDTO;
import org.example.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/login")
    public String login(){
        System.out.println("context  : " + applicationContext);
        System.out.println("getApplicationName : "  + applicationContext.getApplicationName());
        System.out.println("로그인 폼 이동 확인");
        return "login_form";

    }

    @PostMapping("/login")
    public ModelAndView login(MemberDTO member, HttpSession session, HttpServletRequest request) throws SQLException {
        ModelAndView mav = new ModelAndView("alert");

        String loginId = memberService.login(member.getUser_id(), member.getUser_pw());
        if(loginId != null){
            session.setAttribute("loginId", loginId);
            mav.addObject("msg", "login success");
            mav.addObject("path", request.getContextPath() + "/main");
        }
        if(loginId == null){
            mav.addObject("msg", "login failed");
            mav.addObject("path", request.getContextPath() + "/member/login");
        }
        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession sessionm, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("alert");

        sessionm.invalidate();
        mav.addObject("msg", "logout success");
        mav.addObject("path", request.getContextPath() + "/main");
        return mav;
    }

    @GetMapping("/join")
    public String join(){
        System.out.println("context  : " + applicationContext);
        System.out.println("getApplicationName : "  + applicationContext.getApplicationName());
        return "join_form";

    }

   /* @PostMapping("/join")
    public ModelAndView join(MemberDTO member, HttpSession session, HttpServletRequest request) throws SQLException {
        System.out.println("일단 보류");
    }*/
}