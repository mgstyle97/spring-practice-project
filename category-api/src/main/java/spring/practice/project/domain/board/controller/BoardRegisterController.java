package spring.practice.project.domain.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.board.Board;
import spring.practice.project.domain.board.service.BoardRegisterService;
import spring.practice.project.domain.board.command.BoardRegisterCommand;
import spring.practice.project.response.Response;

import javax.validation.Valid;

@RestController
@RequestMapping("/board")
public class BoardRegisterController {

    private BoardRegisterService service;

    @Autowired
    public void setService(final BoardRegisterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Response> register(@RequestBody @Valid final BoardRegisterCommand command) {
        service.register(command);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("Successfully register new board"));
    }

    @GetMapping("/{id}")
    public Board getBoards(@PathVariable("id") @Valid final Long id) {
        Board board = service.getDao().selectById(id);
        board.increaseViews();
        service.getDao().update(board);
        return board;
    }

}
