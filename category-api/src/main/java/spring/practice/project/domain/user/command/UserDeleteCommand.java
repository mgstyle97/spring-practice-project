package spring.practice.project.domain.user.command;

public class UserDeleteCommand {

    private String id;
    private String password;

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

}
