package yun.test.web01;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-11
 * Github : https://github.com/YeoHoonYun
 */
class BoardMethod {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private List<Board> list;
    private File file;
    public int num = 0;
    ServletDAO servletDAO;

    public BoardMethod(HttpServletRequest req, HttpServletResponse resp, yun.test.web01.ServletDAO servletDAO, List<Board> list, File file) {
        this.servletDAO = servletDAO;
        this.list  = list;
        this.req = req;
        this.resp = resp;
        this.file = file;
    }

    private List<Board> getBoards(ServletDAO ServletDAO, List<Board> list, File file) {
        if (file.exists()) {
            System.out.println("exist");
            list = ServletDAO.readFile(file);
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

    public void invoke() throws IOException, ServletException {
        if(req.getPathInfo().equals("/insert")){
            boardInsert(req, resp, servletDAO, list, file);

        }else if(req.getPathInfo().equals("/select")){
            boardSelect(req, resp, servletDAO, list, file);

        }else if(req.getPathInfo().equals("/main")){
            boardMain(req, resp, servletDAO, list, file);

        }else if(req.getPathInfo().equals("/write")){
            boardWrite(req, resp);
        }else if(req.getPathInfo().contains("/detail")){
            boardDetail(req, resp, servletDAO, list, file);
        }else if(req.getPathInfo().contains("/update")){
            list = getBoards(servletDAO, list, file);
            System.out.println(req.getPathInfo());
            list.get(Integer.parseInt(req.getPathInfo().substring(8)) - 1);
            req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req,resp);
        }else if(req.getPathInfo().contains("/delete")){
            list = getBoards(servletDAO, list, file);
            list.remove(Integer.parseInt(req.getPathInfo().substring(8))-1);
            servletDAO.writeFile(list, file);
            resp.sendRedirect("/board/select");
        }
    }

    private void boardInsert(HttpServletRequest req, HttpServletResponse resp, ServletDAO servletDAO, List<Board> list, File file) throws IOException {
        list = getBoards(servletDAO, list, file);
        list.add(new Board(++num,this.get(req,"title"),this.get(req,"content"),this.get(req,"id"), this.get(req,"passwd")));
        servletDAO.writeFile(list, file);
        resp.sendRedirect("/board/select");
    }

    private void boardSelect(HttpServletRequest req, HttpServletResponse resp, ServletDAO servletDAO, List<Board> list, File file) throws ServletException, IOException {
        list = getBoards(servletDAO, list, file);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req,resp);
    }

    private void boardMain(HttpServletRequest req, HttpServletResponse resp, ServletDAO servletDAO, List<Board> list, File file) throws ServletException, IOException {
        list = getBoards(servletDAO, list, file);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req,resp);
    }

    private void boardWrite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/write.jsp").forward(req,resp);
    }

    private void boardDetail(HttpServletRequest req, HttpServletResponse resp, ServletDAO servletDAO, List<Board> list, File file) throws ServletException, IOException {
        list = getBoards(servletDAO, list, file);
        req.setAttribute("board", list.get(Integer.parseInt(req.getPathInfo().substring(8)) - 1));
        req.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(req,resp);
    }
}
