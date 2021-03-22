package spring.practice.project.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.user.NotFoundException;
import spring.practice.project.response.Response;
import spring.practice.project.domain.user.User;
import spring.practice.project.domain.user.UserDao;
import spring.practice.project.domain.user.command.UserRegisterCommand;
import spring.practice.project.domain.user.service.UserRegisterService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRegisterController {

    private UserRegisterService service;

    @Autowired
    public void setService(final UserRegisterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Response> register(@RequestBody @Valid UserRegisterCommand command) {
        service.register(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully register new user"));
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") @Valid String id) {
        UserDao userDao = service.getUserDao();
        User user = userDao.selectById(id);

        return user;
    }


}
