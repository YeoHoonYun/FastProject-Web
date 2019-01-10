package yun.test.web01;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-09
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "board", urlPatterns = "/board/*")
public class BoardServlet extends HttpServlet {
    public static int num = 0;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        MainServlet mainServlet = new MainServlet();
        List<Board> list = new ArrayList<>();
        File file = new File("C:\\Users\\cjswo\\IdeaProjects\\web01\\boardData");
        if(req.getPathInfo().equals("/insert")){
            list = getBoards(mainServlet, list, file);
            list.add(new Board(++num,this.get(req,"title"),this.get(req,"content"),this.get(req,"id"), this.get(req,"passwd")));
            mainServlet.writeFile(list, file);
            resp.sendRedirect("/board/select");

        }else if(req.getPathInfo().equals("/select")){
            list = getBoards(mainServlet, list, file);
            req.setAttribute("list", list);
            RequestDispatcher requestDispatcher1 = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
            requestDispatcher1.forward(req,resp);

        }else if(req.getPathInfo().equals("/main")){
            RequestDispatcher requestDispatcher2 = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
            list = getBoards(mainServlet, list, file);
            req.setAttribute("list", list);
            requestDispatcher2.forward(req,resp);

        }else if(req.getPathInfo().equals("/write")){
            RequestDispatcher requestDispatcher3 = req.getRequestDispatcher("/WEB-INF/views/write.jsp");
            requestDispatcher3.forward(req,resp);

        }else if(req.getPathInfo().contains("/detail")){
            int n = Integer.parseInt(req.getPathInfo().substring(8)) - 1;
            list = getBoards(mainServlet, list, file);
            req.setAttribute("board", list.get(n));
            RequestDispatcher requestDispatcher3 = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
            requestDispatcher3.forward(req,resp);
        }
    }

    private List<Board> getBoards(MainServlet mainServlet, List<Board> list, File file) {
        if (file.exists()) {
            System.out.println("exist");
            list = mainServlet.readFile(file);
            this.num = list.size();
        }
        return list;
    }

    public String get(HttpServletRequest req, String string){
        return req.getParameter(string);
    }
}
