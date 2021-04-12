package spring.practice.project.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.global.response.Response;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public ResponseEntity<Response> logout(HttpSession session) {

        if(session.isNew()) {
            session.invalidate();
            throw new InvalidApproachException();
        }

        session.invalidate();
        return ResponseEntity.ok(new Response("Successfully logout"));

    }

}
