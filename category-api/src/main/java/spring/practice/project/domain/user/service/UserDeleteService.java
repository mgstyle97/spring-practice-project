package spring.practice.project.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.practice.project.domain.global.exception.NotFoundException;
import spring.practice.project.domain.user.User;
import spring.practice.project.domain.user.UserDao;
import spring.practice.project.domain.user.command.UserDeleteCommand;
import spring.practice.project.domain.user.exception.WrongIdPasswordException;

@Component
public class UserDeleteService {

    private UserDao dao;

    @Autowired
    public void setDao(final UserDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void delete(final UserDeleteCommand command) {
        User user = this.dao.selectById(command.getId());
        if(user == null) {
            throw new NotFoundException();
        }
        if(!user.matchPassword(command.getPassword())) {
            throw new WrongIdPasswordException();
        }

        this.dao.deleteById(command.getId());
    }

}
