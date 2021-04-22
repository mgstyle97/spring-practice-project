package spring.practice.project.domain.comment.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDeleteCommand {

    private Long id;
    private String writer;
    @JsonProperty("board_id")
    private Long boardId;

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
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
