package yun.test.web01;

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
public class BoardController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 각각의 board 관련 비어있는 인스턴스 생성
        List<Board> list = new ArrayList<>();
        Board board = new Board();
        BoardDB boardDB = new BoardDB();
        BoardDAO boardDAO = new BoardDAO();

        // HttpServlet 설정 및 데이터 저장 파일 경로 선언
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        File file = new File("C:\\Users\\cjswo\\IdeaProjects\\web01\\boardData");

        // 데이터 입력명령 호출
        if(req.getPathInfo().equals("/insert")){
            boardDAO.boardInsert(req, resp, boardDB, list, file);

        }

        // 게시판 리스트
        else if(req.getPathInfo().equals("/main")){
            boardDAO.boardMain(req, resp, boardDB, list, file);
        }

        // 게시물 작성
        else if(req.getPathInfo().contains("/write")){
            boardDAO.boardWrite(req, resp, boardDB, list, board, file);
        }

        // 단일 게시물 출력
        else if(req.getPathInfo().contains("/detail")){
            boardDAO.boardDetail(req, resp, boardDB, list, file);

        }

        // 단일 게시물 수정
        else if(req.getPathInfo().contains("/update")){
            boardDAO.boardUpdate(req, resp, boardDB, list, board, file);

        }

        // 단일 게시물 삭제
        else if(req.getPathInfo().contains("/delete")){
            boardDAO.boardDelete(req, resp, boardDB, list, file);
        }
    }

}
