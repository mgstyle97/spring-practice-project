package spring.practice.project.domain.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.project.domain.category.commond.CategoryModifyCommand;
import spring.practice.project.domain.category.service.CategoryModifyService;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.global.response.Response;
import spring.practice.project.domain.user.User;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryModifyController {

    private CategoryModifyService service;

    @Autowired
    public void setService(final CategoryModifyService service) {
        this.service = service;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable("id") @Valid final Long id,
                                    @RequestBody @Valid CategoryModifyCommand command,
                                    final HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null ||
                !user.isAdmin() ||
                id.equals(command.getId())) {
            throw new InvalidApproachException();
        }

        this.service.modify(command);

        return ResponseEntity.ok(new Response("Successfully modified category data"));
    }

}
