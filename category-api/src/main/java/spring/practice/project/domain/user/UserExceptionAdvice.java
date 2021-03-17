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

@RestControllerAdvice
public class UserExceptionAdvice {

}