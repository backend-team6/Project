package org.example.my_simple_board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.my_simple_board.model.member.MemberDTO;
import org.example.my_simple_board.model.member.MemberRepository;
import org.example.my_simple_board.model.member.MemberRepositoryMysql;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signup.do")
public class SignupServlet extends HttpServlet {
    private MemberRepository memberRepo = MemberRepositoryMysql.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //회원가입
        String user_id = req.getParameter("user_id");
        String user_pw = req.getParameter("user_pw");

        MemberDTO memberDTO = new MemberDTO(user_id, user_pw);
        try{
            int result = memberRepo.insertMember(memberDTO);
            if(result == 1){
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
