package spring.practice.project.domain.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.practice.project.domain.category.exception.CategoryNotFoundException;
import spring.practice.project.response.Response;

@RestControllerAdvice("spring.practice.project.domain.category")
public class CategoryExceptionAdvice {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Response> handleNoCategory() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response("No category data"));
    }

}
