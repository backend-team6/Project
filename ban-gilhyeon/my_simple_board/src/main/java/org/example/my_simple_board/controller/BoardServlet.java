package org.example.my_simple_board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.my_simple_board.model.board.BoardDTO;
import org.example.my_simple_board.model.board.BoardRepository;
import org.example.my_simple_board.model.board.BoardRepositoryMysql;
import org.example.my_simple_board.model.member.MemberRepository;
import org.example.my_simple_board.model.member.MemberRepositoryMysql;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/board.do")
public class BoardServlet extends HttpServlet {
    private BoardRepository repo = BoardRepositoryMysql.getInstance();
    private MemberRepository memberRepo = MemberRepositoryMysql.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try{
            if("login".equals(action)){
                req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
            } else if ("signup".equals(action)) {
                req.getRequestDispatcher("WEB-INF/views/signup.jsp").forward(req, resp);
            } else if ("list".equals(action)) {
                List<BoardDTO> boardList = repo.selectAll();
                req.setAttribute("boardList", boardList);
                req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
            } else if ("wirteForm".equals(action)) {
                if(true){
                    req.getRequestDispatcher("/WEB-INF/views/wirteForm.jsp").forward(req, resp);
                }
                else {
                    req.getRequestDispatcher("WEB-INF/common/error.jsp").forward(req, resp);
                }
            } else if ("view".equals(action)) {
                String noParam = req.getParameter("no");
                int no = Integer.parseInt(noParam);

                BoardDTO board = repo.selectOne(no);
                req.setAttribute("board", board);
                req.getRequestDispatcher("/WEB-INF/views/view.jsp").forward(req, resp);
            }
        }catch (SQLException e){
            e.printStackTrace();
            req.getRequestDispatcher("WEB-INF/common/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String writer = req.getParameter("writer");

        BoardDTO board = new BoardDTO(title, content, writer);
        try{
            int result = repo.insert(board);
            if(result == 1){
                req.getRequestDispatcher("/WEB-INF/common/success.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/common/fail.jsp").forward(req, resp);
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
