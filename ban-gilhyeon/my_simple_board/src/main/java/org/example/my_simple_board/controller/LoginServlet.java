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

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    private MemberRepository memberRepo = MemberRepositoryMysql.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_id = req.getParameter("user_id");
        String user_pw = req.getParameter("user_pw");

        try{
            //String result = memberRepo.selectOneMember(user_id).getPw();
            MemberDTO memberDTO = memberRepo.selectOneMember(user_id);
            if(user_pw.equals(memberDTO.getPw())){
                req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
            }
            else{
                req.getRequestDispatcher("/WEB-INF/common/error.jsp").forward(req, resp);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
