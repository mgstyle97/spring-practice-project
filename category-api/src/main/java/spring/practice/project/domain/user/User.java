package spring.practice.project.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class User {

    private String id;
    @JsonIgnore
    private String password;
    private String name;
    private String nick;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime redDate;
    private boolean isAdmin;

    public User(final String id, final String password,
                final String name, final String nick,
                final LocalDateTime redDate, final boolean isAdmin) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.nick = nick;
        this.redDate = redDate;
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return this.id;
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

    public LocalDateTime getRedDate() {
        return redDate;
    }

    public void setRedDate(final LocalDateTime redDate) {
        this.redDate = redDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(final boolean admin) {
        isAdmin = admin;
    }
}
