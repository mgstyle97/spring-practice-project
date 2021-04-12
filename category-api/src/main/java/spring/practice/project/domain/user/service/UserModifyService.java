package spring.practice.project.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.practice.project.domain.user.User;
import spring.practice.project.domain.user.UserDao;
import spring.practice.project.domain.user.command.UserModifyCommand;
import spring.practice.project.domain.user.exception.NoEqualsPassword2ConfirmPasswordException;

import java.time.LocalDateTime;

@Component
public class UserModifyService {

    private UserDao dao;

    @Autowired
    public void setDao(final UserDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void modify(final UserModifyCommand command) {
        User user = this.dao.selectByNick(command.getNick());
        if(!user.matchPassword(command.getConfirmPassword())) {
            throw new NoEqualsPassword2ConfirmPasswordException();
        }

        User updateUser = new User(
                user.getId(), command.getChangePassword(),
                command.getName(), command.getNick(),
                user.getRedDate(), user.isAdmin()
        );
        updateUser.setModifiedTime(LocalDateTime.now());

        this.dao.update(updateUser);
    }

}
