package com.grepp.controller;

import com.grepp.model.BoardDTO;
import com.grepp.model.BoardService;
import com.grepp.model.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet("/board.do")
public class BoardServlet extends HttpServlet {
    private BoardService boardService=BoardService.getInstance();
    private UserService userService=UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");

        try {
            if ("list".equals(action)) {
                List<BoardDTO> list = boardService.viewList();
                req.setAttribute("bList",list);
                req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req,resp);
            } else if ("writeForm".equals(action)) {
                HttpSession session=req.getSession();
                String loginId=(String)session.getAttribute("loginId");
                if(loginId==null) {
                    req.setAttribute("msg", "로그인을 먼저 해주세요");
                    req.setAttribute("path","main.do");
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req,resp);
                }else {
                    req.getRequestDispatcher("/WEB-INF/views/writeForm.jsp").forward(req, resp);
                }
            }else if("view".equals(action)){
                String no=req.getParameter("no");
                boardService.plusReadCount(Integer.parseInt(no));
                BoardDTO board=boardService.viewOne(Integer.parseInt(no));
                req.setAttribute("board",board);
                req.getRequestDispatcher("/WEB-INF/views/board.jsp").forward(req,resp);

            }else if("update".equals(action)){
                HttpSession session=req.getSession();
                String loginId=(String)session.getAttribute("loginId");

                int no=Integer.parseInt(req.getParameter("no"));
                String userId=userService.getId(no);
                if(userId!=null && userId.equals(loginId)){
                    BoardDTO board= boardService.viewOne(no);
                    req.setAttribute("board", board);
                    req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req,resp);
                }else{
                    req.setAttribute("msg", "글 작성자만 수정할 수 있습니다.");
                    req.setAttribute("path",req.getContextPath()+"/board.do?action=view&no="+no);
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req,resp);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");

        try {
            if ("write".equals(action)) {
                HttpSession session=req.getSession();
                String loginId=(String)session.getAttribute("loginId");
                String writer=userService.getUserName(loginId);

                String title=req.getParameter("title");
                String content=req.getParameter("content");
                BoardDTO board = new BoardDTO(writer, title, content);

                int result=boardService.write(board);
                if(result==1){
                    resp.sendRedirect(req.getContextPath()+"/alert.do?action=write");
                }
            }else if("update".equals(action)){
                String no=req.getParameter("no");
                String title = req.getParameter("title");
                String content = req.getParameter("content");
                BoardDTO board = new BoardDTO(title, content);
                board.setNo(Integer.parseInt(no));

                int result = boardService.update(board);
                if (result == 1) {
                    resp.sendRedirect(req.getContextPath() + "/alert.do?action=update");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
