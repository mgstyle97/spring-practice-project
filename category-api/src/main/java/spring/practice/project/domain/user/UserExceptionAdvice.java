package spring.practice.project.domain.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.practice.project.domain.user.exception.DuplicateNickException;
import spring.practice.project.domain.user.exception.DuplicateUserException;
import spring.practice.project.domain.user.exception.UserNotFoundException;
import spring.practice.project.domain.user.exception.NoEqualsPassword2ConfirmPasswordException;
import spring.practice.project.response.Response;

import java.util.stream.Collectors;

@RestControllerAdvice("spring.practice.project.domain.user")
public class UserExceptionAdvice {

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<Response> handleDupIdExp() {
        return getBadRequestResponseEntity("Duplicate user id");
    }

    @ExceptionHandler(DuplicateNickException.class)
    public ResponseEntity<Response> handleDupNickExp() {
        return getBadRequestResponseEntity("Duplicate user nick");
    }

    @ExceptionHandler(NoEqualsPassword2ConfirmPasswordException.class)
    public ResponseEntity<Response> handleNoEqualsPw2ConfirmPwExp() {
        return getBadRequestResponseEntity("No match password with confirm password");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleMemberNotFoundExp() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response("No user data"));
    }

    private ResponseEntity<Response> getBadRequestResponseEntity(final String message) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response(message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleBindData(MethodArgumentNotValidException ex) {
        String errorCodes = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getCodes()[0])
                .collect(Collectors.joining("."));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("errorCodes : " + errorCodes));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleInvalidJSONFormatExp() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Invlid JSON Format"));
    }

}
