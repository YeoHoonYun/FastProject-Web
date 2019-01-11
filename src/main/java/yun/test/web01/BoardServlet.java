package yun.test.web01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.util.*;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-09
 * Github : https://github.com/YeoHoonYun
 */
@WebServlet(name = "board", urlPatterns = "/board/*")
public class BoardServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        ServletDAO ServletDAO = new ServletDAO();
        List<Board> list = new ArrayList<>();
        File file = new File("C:\\Users\\cjswo\\IdeaProjects\\web01\\boardData");

        // request, response, ServletDAO() - 데이터 CURD, 데이터
        new BoardMethod(req, resp, ServletDAO, list, file).invoke();
    }

}
