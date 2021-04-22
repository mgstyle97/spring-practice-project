package spring.practice.project.domain.comment.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentModifyCommand {

    private Long id;
    private String contents;
    private boolean access;
    private String writer;
    private Long boardId;

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
