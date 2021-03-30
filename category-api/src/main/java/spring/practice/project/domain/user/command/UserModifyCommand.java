package spring.practice.project.domain.user.command;

import javax.validation.constraints.NotBlank;

public class UserModifyCommand {

    @NotBlank(message = "확인 비밀번호를 입력해야 합니다.")
    private String confirmPassword;
    @NotBlank(message = "변경할 비밀번호 혹은 기존 비밀번호를 입력해야 합니다.")
    private String changePassword;
    @NotBlank(message = "변경할 이름 혹은 기존 이름을 입력해야 합니다.")
    private String name;
    @NotBlank(message = "변경할 닉네임 혹은 기존 닉네임을 입력해야 합니다.")
    private String nick;

    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setChangePassword(final String changePassword) {
        this.changePassword = changePassword;
    }

    public String getChangePassword() {
        return this.changePassword;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setNick(final String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return this.nick;
    }

}
