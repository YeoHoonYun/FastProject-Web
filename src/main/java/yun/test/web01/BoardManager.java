package yun.test.web01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-10
 * Github : https://github.com/YeoHoonYun
 */
public class BoardManager implements Serializable {
    List<Board> boards;

    public BoardManager() {
        boards = new ArrayList<>();
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
