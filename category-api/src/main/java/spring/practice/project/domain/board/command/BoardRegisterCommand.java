package spring.practice.project.domain.board.command;

import javax.validation.constraints.NotBlank;

public class BoardRegisterCommand {

    @NotBlank(message = "제목을 입력해야 합니다.")
    private String title;
    @NotBlank(message = "내용을 입력해야 합니다.")
    private String contents;
    private boolean access;
    @NotBlank(message = "게시글의 작성자를 입력해야 합니다.")
    private String writer;
    private Long categoryId;

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
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

    public void setCategoryId(final Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

}
