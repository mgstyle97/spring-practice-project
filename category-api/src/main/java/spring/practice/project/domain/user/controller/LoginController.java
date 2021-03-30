package spring.practice.project.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.InvalidApproachException;
import spring.practice.project.domain.user.User;
import spring.practice.project.domain.user.command.LoginCommand;
import spring.practice.project.domain.user.service.AuthService;
import spring.practice.project.response.Response;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private AuthService authService;

    @Autowired
    public void setAuthService(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Response> login(@RequestBody @Valid LoginCommand command,
                                          HttpSession session,
                                          HttpServletResponse response) {
        User userInfo = this.authService.authenticate(command);
        session.setAttribute("user", userInfo);

        Cookie userIdCookie = new Cookie("ID", command.getId());
        userIdCookie.setPath("/");  // 모든 경로에서 접근 가능
        if(command.isRememberId()) {
            userIdCookie.setMaxAge(60 * 60);
        } else {
            userIdCookie.setMaxAge(0);
        }
        response.addCookie(userIdCookie);

        return ResponseEntity.ok(new Response("Successfully login"));
    }

    @GetMapping
    public ResponseEntity<?> sessionTest(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.invalidate();
            throw new InvalidApproachException();
        }

        return ResponseEntity.ok(session.getAttribute("user"));
    }

}
