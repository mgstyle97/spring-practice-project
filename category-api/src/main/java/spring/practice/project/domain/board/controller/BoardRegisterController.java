package spring.practice.project.domain.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.board.Board;
import spring.practice.project.domain.board.BoardDao;
import spring.practice.project.domain.board.service.BoardRegisterService;
import spring.practice.project.domain.board.command.BoardRegisterCommand;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.global.response.Response;
import spring.practice.project.domain.user.User;

import javax.servlet.http.HttpSession;
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
    @Transactional
    public Board getBoard(@PathVariable("id") @Valid final Long id) {
        BoardDao boardDao = service.getDao();
        Board board = boardDao.selectById(id);
        board.setCommentList(boardDao.selectByBoardId2CommentList(id));
        board.increaseViews();
        boardDao.increseViews(id);
        return board;
    }

}
