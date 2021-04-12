package spring.practice.project.domain.board.command;

import javax.validation.constraints.NotNull;

public class BoardModifyCommand {

    @NotNull(message = "Invalid Approach")
    private Long id;
    private String title;
    private String contents;
    private boolean access;

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

}
