package spring.practice.project.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.InvalidApproachException;
import spring.practice.project.domain.user.User;
import spring.practice.project.domain.user.UserDao;
import spring.practice.project.domain.user.command.UserModifyCommand;
import spring.practice.project.domain.user.exception.NoEqualsPassword2ConfirmPasswordException;
import spring.practice.project.response.Response;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users/{id}")
public class UserModifyController {

    private UserDao dao;

    @Autowired
    public void setDao(final UserDao dao) {
        this.dao = dao;
    }

    @PatchMapping
    public ResponseEntity<Response> modify(@PathVariable("id") @Valid String id,
                                           @RequestBody @Valid UserModifyCommand command,
                                           HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(!user.getId().equals(id)) {
            throw new InvalidApproachException();
        }
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

        return ResponseEntity.ok(new Response("Successfully Modified user data"));
    }

}
