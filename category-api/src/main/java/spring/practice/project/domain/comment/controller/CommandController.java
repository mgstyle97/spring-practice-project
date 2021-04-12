package spring.practice.project.domain.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.practice.project.domain.comment.command.CommentRegisterCommand;
import spring.practice.project.domain.comment.service.CommentRegisterService;
import spring.practice.project.domain.global.response.Response;

import javax.validation.Valid;

@RestController
public class CommandController {

    private CommentRegisterService service;

    @Autowired
    public void setService(final CommentRegisterService service) {
        this.service = service;
    }

    @PostMapping("/board/{id}/comment")
    public ResponseEntity<Response> regist(@RequestBody @Valid final CommentRegisterCommand command) {
        this.service.regist(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("Successfully register new comment"));
    }

}
