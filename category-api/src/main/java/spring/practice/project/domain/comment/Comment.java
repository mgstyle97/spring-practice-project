package spring.practice.project.domain.comment;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Comment {

    private Long id;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime regDate;
    private boolean access;
    private String writer;
    private Long boardId;

    public Comment(final String contents, final boolean access,
                   final String writer, final Long boardId) {
        this.contents = contents;
        this.access = access;
        this.writer = writer;
        this.boardId = boardId;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setContents(final String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return this.contents;
    }

    public void setRegDate(final LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public LocalDateTime getRegDate() {
        return this.regDate;
    }

    public void setAccess(final boolean access) {
        this.access = access;
    }

    public boolean isAccess() {
        return this.access;
    }

    public void setWriter(final String writer) {
        this.writer = writer;
    }

    public String getWriter() {
        return this.writer;
    }

    public void setBoardId(final Long boardId) {
        this.boardId = boardId;
    }

    public Long getBoardId() {
        return this.boardId;
    }

}
