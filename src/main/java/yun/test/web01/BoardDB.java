package yun.test.web01;

import java.io.*;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-09
 * Github : https://github.com/YeoHoonYun
 */
public class BoardDB {
    private BoardDAO boardDAO;

    public BoardDB() {
        this.boardDAO = new BoardDAO();
    }

    public List<Board> readFile(File file){
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream(file));
            boardDAO = (BoardDAO) in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return boardDAO.getBoards();
    }

    public void writeFile(List<Board> boardList, File file){
        ObjectOutputStream out = null;
        boardDAO.setBoards(boardList);
        try{
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(boardDAO);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
