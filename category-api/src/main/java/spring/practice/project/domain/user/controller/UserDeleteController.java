package spring.practice.project.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.user.command.UserDeleteCommand;
import spring.practice.project.domain.user.service.UserDeleteService;
import spring.practice.project.domain.global.response.Response;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserDeleteController {

    private UserDeleteService service;

    @Autowired
    public void setService(final UserDeleteService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable @Valid final String id,
                                        @RequestBody @Valid final UserDeleteCommand command) {
        if(!id.equals(command.getId())) {
            throw new InvalidApproachException();
        }

        this.service.delete(command);

        return ResponseEntity.ok(new Response("Successfully remove user data"));
    }

}
