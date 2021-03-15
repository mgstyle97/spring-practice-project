package spring.practice.project.domain.category;

import spring.practice.project.domain.board.Board;

import java.util.List;

public class Category {

    private Long id;
    private String title;
    private String info;
    private List<Board> boardList;

    public Category(final String title, final String info) {
        this.title = title;
        this.info = info;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

    public void setBoardList(final List<Board> boardList) {
        this.boardList = boardList;
    }
}
