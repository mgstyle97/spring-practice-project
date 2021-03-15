package spring.practice.project.domain.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.practice.project.domain.category.exception.CategoryNotFoundException;
import spring.practice.project.response.Response;

import java.util.stream.Collectors;

@RestControllerAdvice("spring.practice.project.domain.category")
public class CategoryExceptionAdvice {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Response> handleNoCategory() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response("No category data"));
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
