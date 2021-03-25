package spring.practice.project.domain.comment.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentRegisterCommand {

    @NotBlank(message = "댓글에 내용을 입력해야 합니다.")
    private String contents;
    private boolean access;
    @NotBlank(message = "작성자가 없습니다.")
    private String writer;
    @NotNull(message = "보드의 아이디가 없습니다.")
    private Long boardId;

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
