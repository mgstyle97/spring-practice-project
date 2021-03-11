package spring.practice.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.practice.project.exception.DuplicateNickException;
import spring.practice.project.exception.DuplicateUserException;
import spring.practice.project.exception.NoEqualsPassword2ConfirmPasswordException;

import java.time.LocalDateTime;

@Component
public class UserRegisterService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void register(final UserRegisterCommand command) {
        if(userDao.selectById(command.getId()) != null) {
            throw new DuplicateUserException();
        } else if(userDao.selectByNick(command.getNick()) != null) {
            throw new DuplicateNickException();
        }
        if(!command.isPasswordEquals2ConfirmPassword()) {
            throw new NoEqualsPassword2ConfirmPasswordException();
        }
        User newUser = new User(
                command.getId(), command.getPassword(),
                command.getName(), command.getNick(),
                LocalDateTime.now(), command.isAdmin()
        );

        userDao.insert(newUser);
    }

}
