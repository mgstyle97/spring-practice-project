package spring.practice.project.domain.board;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Board {

    private Long id;
    private String title;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime regDate;
    private boolean access;
    private Long views;
    private String writer;

    public Board(final String title, final String contents,
                 final boolean access, final String writer) {
        this.title = title;
        this.contents = contents;
        this.access = access;
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(final String contents) {
        this.contents = contents;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(final LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(final boolean access) {
        this.access = access;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(final Long views) {
        this.views = views;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(final String writer) {
        this.writer = writer;
    }

    public void increaseViews() {
        this.views++;
    }
}
