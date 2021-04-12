package spring.practice.project.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.user.User;
import spring.practice.project.domain.user.UserDao;
import spring.practice.project.domain.user.command.UserModifyCommand;
import spring.practice.project.domain.user.exception.NoEqualsPassword2ConfirmPasswordException;
import spring.practice.project.domain.global.response.Response;
import spring.practice.project.domain.user.service.UserModifyService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/users/{id}")
public class UserModifyController {

    private UserModifyService service;

    @Autowired
    public void setService(final UserModifyService service) {
        this.service = service;
    }

    @PatchMapping
    public ResponseEntity<Response> modify(@PathVariable("id") @Valid String id,
                                           @RequestBody @Valid UserModifyCommand command,
                                           HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(!user.getId().equals(id)) {
            throw new InvalidApproachException();
        }

        this.service.modify(command);

        return ResponseEntity.ok(new Response("Successfully modified user data"));
    }

}
