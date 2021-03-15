package spring.practice.project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.practice.project.response.Response;

import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = {
        "spring.practice.project.domain.board.controller",
        "spring.practice.project.domain.users.controller"
})
public class CommonExceptionAdvice {



}
