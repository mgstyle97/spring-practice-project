package spring.practice.project.domain.user.command;

import javax.validation.constraints.NotBlank;

public class LoginCommand {

    @NotBlank(message = "ID를 입력해주세요.")
    private String id;
    @NotBlank(message = "PASSWORD를 입력해주세요.")
    private String password;
    private boolean isRememberId;

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setIsRememberId(final boolean isRememberId) {
        this.isRememberId = isRememberId;
    }

    public boolean isRememberId() {
        return this.isRememberId;
    }

}
