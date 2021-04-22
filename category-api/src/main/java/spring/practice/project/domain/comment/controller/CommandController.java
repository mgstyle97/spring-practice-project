package spring.practice.project.domain.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.comment.command.CommentDeleteCommand;
import spring.practice.project.domain.comment.command.CommentModifyCommand;
import spring.practice.project.domain.comment.command.CommentRegisterCommand;
import spring.practice.project.domain.comment.service.CommentDeleteService;
import spring.practice.project.domain.comment.service.CommentModifyService;
import spring.practice.project.domain.comment.service.CommentRegisterService;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.global.response.Response;

import javax.validation.Valid;

@RestController
public class CommandController {

    private CommentRegisterService registerService;
    private CommentModifyService  modifyService;
    private CommentDeleteService deleteService;

    @Autowired
    public void setRegisterService(final CommentRegisterService registerService) {
        this.registerService = registerService;
    }

    @Autowired
    public void setModifyService(final CommentModifyService modifyService) {
        this.modifyService = modifyService;
    }

    @Autowired
    public void setDeleteService(final CommentDeleteService deleteService) {
        this.deleteService = deleteService;
    }

    @PostMapping("/board/{boardId}/comment")
    public ResponseEntity<Response> regist(@PathVariable("boardId") @Valid Long boardId,
                                           @RequestBody @Valid final CommentRegisterCommand command) {
        if (!boardId.equals(command.getBoardId())) {
            throw new InvalidApproachException();
        }
        this.registerService.regist(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("Successfully register new comment"));
    }

    @PatchMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<Response> modify(@PathVariable("boardId") @Valid Long boardId,
                                           @PathVariable("commentId") @Valid Long commentId,
                                           @RequestBody @Valid CommentModifyCommand command) {
        if ((boardId.equals(command.getBoardId())) &&
                (commentId.equals(command.getId()))) {
            modifyService.modify(command);

            return ResponseEntity.ok(new Response("Successfully modify comment data"));
        }

        return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(new Response("Failed to modify comment data"));
    }

    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<Response> delete(@PathVariable("boardId") @Valid Long boardId,
                                           @PathVariable("commentId") @Valid Long commentId,
                                           @RequestBody @Valid CommentDeleteCommand command) {
        if (!(boardId.equals(command.getBoardId())) ||
                !(commentId.equals(command.getId()))) {
            throw new InvalidApproachException();
        }

        deleteService.delete(command);

        return ResponseEntity.ok(new Response("Successfully remove comment data"));
    }

}
