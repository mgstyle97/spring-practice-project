package spring.practice.project.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.practice.project.domain.user.User;
import spring.practice.project.domain.user.UserDao;
import spring.practice.project.domain.user.command.UserLoginCommand;

@Component
public class UserLoginService {

    private UserDao dao;

    @Autowired
    public void setDao(final UserDao dao) {
        this.dao = dao;
    }

    public boolean login(final UserLoginCommand command) {
        User user = this.dao.selectById(command.getId());
        if(user.getPassword().equals(command.getPassword())) {
            
        }
    }

}
