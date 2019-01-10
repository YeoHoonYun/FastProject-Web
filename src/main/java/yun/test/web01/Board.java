package yun.test.web01;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-09
 * Github : https://github.com/YeoHoonYun
 */
public class Board implements Serializable {
    private int num;
    private String title;
    private String content;
    private String id;
    private String passwd;
    private String localDateTime;

    public Board() {
        this.localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Board(int num, String title, String content, String id, String passwd) {
        this();
        this.num = num;
        this.title = title;
        this.content = content;
        this.id = id;
        this.passwd = passwd;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
