package spring.practice.project.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.practice.project.domain.InvalidApproachException;
import spring.practice.project.response.Response;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
