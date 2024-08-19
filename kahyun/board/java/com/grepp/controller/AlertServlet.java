package com.grepp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/alert.do")
public class AlertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("write".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(req, resp);
        }else if("update".equals(action)){
            req.getRequestDispatcher("/WEB-INF/views/successUpdate.jsp").forward(req, resp);
        }else if("login".equals(action)){
            String state=req.getParameter("state");
            if("success".equals(state)){
                req.getRequestDispatcher("/WEB-INF/views/successLogin.jsp").forward(req, resp);
            }else if("fail".equals(state)){
                req.getRequestDispatcher("/WEB-INF/views/failLogin.jsp").forward(req, resp);
            }
        }else if("signUp".equals(action)){
            String state=req.getParameter("state");
            if("success".equals(state)){
                req.getRequestDispatcher("/WEB-INF/views/successSignUp.jsp").forward(req, resp);
            }else if("fail".equals(state)){
                req.getRequestDispatcher("/WEB-INF/views/failSignUp.jsp").forward(req, resp);
            }
        }
    }
}
