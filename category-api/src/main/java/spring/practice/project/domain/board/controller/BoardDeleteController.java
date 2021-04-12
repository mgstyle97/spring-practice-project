package spring.practice.project.domain.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.practice.project.domain.board.service.BoardDeleteService;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.global.response.Response;
import spring.practice.project.domain.user.User;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/board")
public class BoardDeleteController {

    private BoardDeleteService service;

    @Autowired
    public void setService(final BoardDeleteService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") @Valid final Long id,
                                    final HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            throw new InvalidApproachException();
        }

        this.service.delete(id, user);

        return ResponseEntity.ok(new Response("Successfully remove board data"));
    }

}
