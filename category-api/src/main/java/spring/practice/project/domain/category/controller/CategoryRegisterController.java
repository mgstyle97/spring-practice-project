package spring.practice.project.domain.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.category.Category;
import spring.practice.project.domain.category.commond.CategoryRegisterCommand;
import spring.practice.project.domain.category.service.CategoryRegisterService;
import spring.practice.project.response.Response;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryRegisterController {

    private CategoryRegisterService service;

    @Autowired
    public void setService(final CategoryRegisterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Response> register(@RequestBody @Valid final CategoryRegisterCommand command) {
        service.register(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("Successfully register new category"));
    }

    @GetMapping("/{title}")
     public Category getCategory(@PathVariable("title") @Valid final String title) {
        Category category = service.getDao().selectByTitle(title);

        return category;
    }

}
