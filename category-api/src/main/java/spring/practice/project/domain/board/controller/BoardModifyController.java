package spring.practice.project.domain.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.board.command.BoardModifyCommand;
import spring.practice.project.domain.board.service.BoardModifyService;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.global.response.Response;
import spring.practice.project.domain.user.User;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/board")
public class BoardModifyController {

    private BoardModifyService service;

    @Autowired
    public void setService(final BoardModifyService service) {
        this.service = service;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable("id") @Valid final Long id,
                                    @RequestBody @Valid final BoardModifyCommand command,
                                    final HttpSession session) {
        if(!id.equals(command.getId())) {
            throw new InvalidApproachException();
        }
        User user = (User) session.getAttribute("user");

        this.service.modify(command, user);

        return ResponseEntity.ok(new Response("Successfully modified board data"));
    }

}
