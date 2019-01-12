package yun.test.web01;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-10
 * Github : https://github.com/YeoHoonYun
 */
public class BoardDAO implements Serializable {
    List<Board> boards;
    public int num = 0;

    public BoardDAO() {
        boards = new ArrayList<>();
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public List<Board> getBoards(BoardDB BoardDB, List<Board> list, File file) {
        if (file.exists()) {
            list = BoardDB.readFile(file);
            try{
                this.num = list.get(list.size()-1).getNum();
            }catch (Exception e){
                this.num = 0;
            }
        }
        return list;
    }

    public String get(HttpServletRequest req, String string){
        return req.getParameter(string);
    }
    public void boardDelete(HttpServletRequest req, HttpServletResponse resp, BoardDB boardDB, List<Board> list, File file) throws IOException {
        list = getBoards(boardDB, list, file);
        list.remove(Integer.parseInt(req.getPathInfo().substring(8))-1);
        boardDB.writeFile(list, file);
        resp.sendRedirect("/board/main");
    }

    public void boardUpdate(HttpServletRequest req, HttpServletResponse resp, BoardDB boardDB, List<Board> list, Board board , File file) throws IOException {
        list = getBoards(boardDB, list, file);
        board = list.get(Integer.parseInt(req.getPathInfo().substring(8))-1);
        board.setTitle(this.get(req,"title"));
        board.setContent(this.get(req,"content"));
        board.setId(this.get(req,"id"));
        board.setLocalDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        boardDB.writeFile(list, file);
        resp.sendRedirect("/board/main");
    }

    public void boardWrite(HttpServletRequest req, HttpServletResponse resp, BoardDB boardDB, List<Board> list, Board board , File file) throws ServletException, IOException {
        if(req.getPathInfo().substring(6).isEmpty()) {
            req.getRequestDispatcher("/WEB-INF/views/write.jsp").forward(req,resp);
        }else{
            board = getBoards(boardDB, list, file).get(Integer.parseInt(req.getPathInfo().substring(7)) - 1);
            req.setAttribute("board", board);
            req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req,resp);
        }
    }

    public void boardInsert(HttpServletRequest req, HttpServletResponse resp, BoardDB boardDB, List<Board> list, File file) throws IOException {
        list = getBoards(boardDB, list, file);
        list.add(new Board(++num,this.get(req,"title"),this.get(req,"content"),this.get(req,"id"), this.get(req,"passwd")));
        boardDB.writeFile(list, file);
        resp.sendRedirect("/board/main");
    }

    public void boardMain(HttpServletRequest req, HttpServletResponse resp, BoardDB boardDB, List<Board> list, File file) throws ServletException, IOException {
        list = getBoards(boardDB, list, file);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req,resp);
    }

    public void boardDetail(HttpServletRequest req, HttpServletResponse resp, BoardDB boardDB, List<Board> list, File file) throws ServletException, IOException {
        list = getBoards(boardDB, list, file);
        req.setAttribute("board", list.get(Integer.parseInt(req.getPathInfo().substring(8)) - 1));
        req.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(req,resp);
    }
}
