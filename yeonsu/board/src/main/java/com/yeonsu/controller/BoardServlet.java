package com.yeonsu.controller;

import com.yeonsu.model.board.BoardDTO;
import com.yeonsu.model.board.BoardService;
import com.yeonsu.model.user.UserDTO;
import com.yeonsu.model.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
    private BoardService boardService = BoardService.getInstance();
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        String loginId = (String) session.getAttribute("loginId");
        try {
            //글 목록
            if ("list".equals(action)) {
                List<BoardDTO> boardList = boardService.getBoards();
                req.setAttribute("blist", boardList);
                req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);

                //글 작성
            } else if ("writeForm".equals(action)) {
                if (loginId == null) {
                    req.setAttribute("msg", "로그인이 필요합니다.");
                    req.setAttribute("path", req.getContextPath() + "/user?action=login"); //로그인 페이지로 보냄
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("/WEB-INF/views/writeForm.jsp").forward(req, resp);
                }
                //글 상세화면
            } else if ("view".equals(action)) {
                if (loginId == null) {
                    req.setAttribute("msg", "로그인이 필요합니다.");
                    req.setAttribute("path", req.getContextPath() + "/user?action=login"); //로그인 페이지로 보냄
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                } else {
                    String noParam = req.getParameter("no");
                    int no = Integer.parseInt(noParam);
                    BoardDTO boardDTO = boardService.read(no, loginId);

                    req.setAttribute("board", boardDTO);

                    UserDTO userDTO = userService.selectOne(boardDTO.getWriter());
                    req.setAttribute("writer", userDTO.getLoginId());
                    req.getRequestDispatcher("/WEB-INF/views/view.jsp").forward(req, resp);
                }

                //글 수정
            } else if ("update".equals(action)) {
                String noParam = req.getParameter("no");
                int no = Integer.parseInt(noParam);
                BoardDTO boardDTO = boardService.read(no, loginId);

                req.setAttribute("board", boardDTO);

                UserDTO userDTO = userService.selectOne(boardDTO.getWriter());
                req.setAttribute("writer", userDTO.getLoginId());
                req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);

                //글 삭제
            } else if ("delete".equals(action)) {
                String noParam = req.getParameter("no");
                int no = Integer.parseInt(noParam);

                if (boardService.delete(no) == 1) {
                    req.setAttribute("msg", "삭제되었습니다.");
                    req.setAttribute("path", req.getContextPath() + "/board?action=list");
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        BoardDTO boardDTO = new BoardDTO(title, content);

        try {
            int result = 0;
            HttpSession session = req.getSession();
            String loginId = (String) session.getAttribute("loginId");
            if("insert".equals(action)) {
                result = boardService.write(boardDTO, loginId);
                if (result == 1) {
                    req.setAttribute("msg", "글 작성이 완료되었습니다.");
                    req.setAttribute("path", req.getContextPath() + "/board?action=list");
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                }

            } else if ("update".equals(action)) {
                String noParam = req.getParameter("no"); //"1" 이렇게 스트링으로 전달됨
                int no = Integer.parseInt(noParam);
                boardDTO.setNo(no);
                result = boardService.update(boardDTO);
                if (result == 1) {
                    req.setAttribute("msg", "글 수정이 완료되었습니다.");
                    req.setAttribute("path", req.getContextPath() + "/board?action=view&no=" + no);
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
        }
    }
}
