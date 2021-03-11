package spring.practice.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.practice.project.response.Response;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class UserRegisterController {

    private UserRegisterService service;

    @Autowired
    public void setService(final UserRegisterService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody @Valid UserRegisterCommand command) {
        service.register(command);
        URI uri = URI.create("/register");
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully register new user data"));
    }

}
