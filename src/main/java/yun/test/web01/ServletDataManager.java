package yun.test.web01;

import java.io.*;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-09
 * Github : https://github.com/YeoHoonYun
 */
public class ServletDataManager {
    private BoardManager boardManager;

    public ServletDataManager() {
        this.boardManager = new BoardManager();
    }

    public List<Board> readFile(File file){
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream(file));
            boardManager = (BoardManager) in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return boardManager.getBoards();
    }

    public void writeFile(List<Board> boardList, File file){
        ObjectOutputStream out = null;
        boardManager.setBoards(boardList);
        try{
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(boardManager);
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
