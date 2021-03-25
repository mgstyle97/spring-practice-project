package spring.practice.project.domain.user.command;

import javax.validation.constraints.NotBlank;

public class UserRegisterCommand {

    @NotBlank(message = "아이디를 입력해야 합니다.")
    private String id;
    @NotBlank(message = "비밀번호를 입력해야 합니다.")
    private String password;
    @NotBlank(message = "확인 비밀번호를 입력해야 합니다.")
    private String confirmPassword;
    @NotBlank(message = "이름을 입력해야 합니다.")
    private String name;
    @NotBlank(message = "닉네임을 입력해야 합니다.")
    private String nick;
    private boolean isAdmin;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(final String nick) {
        this.nick = nick;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(final boolean admin) {
        isAdmin = admin;
    }

    public boolean isPasswordEquals2ConfirmPassword() {
        return password.equals(confirmPassword);
    }
}
