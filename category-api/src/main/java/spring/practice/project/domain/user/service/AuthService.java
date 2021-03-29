package spring.practice.project.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.practice.project.domain.NotFoundException;
import spring.practice.project.domain.user.User;
import spring.practice.project.domain.user.UserDao;
import spring.practice.project.domain.user.command.LoginCommand;
import spring.practice.project.domain.user.exception.WrongIdPasswordException;

@Component
public class AuthService {

    private UserDao dao;

    @Autowired
    public void setDao(final UserDao dao) {
        this.dao = dao;
    }

    public User authenticate(final LoginCommand command) {
        User user = this.dao.selectById(command.getId());
        if(user == null) {
            throw new NotFoundException();
        }
        if(!user.matchPassword(command.getPassword())) {
            throw new WrongIdPasswordException();
        }
        return user;
    }

}
