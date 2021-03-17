package spring.practice.project.domain.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.practice.project.domain.board.Board;
import spring.practice.project.domain.category.CategoryDao;
import spring.practice.project.domain.category.exception.CategoryNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategorySearchController {

    private CategoryDao categoryDao;

    @Autowired
    public void setCategoryDao(final CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping("/{id}/board")
    public ResponseEntity<List<Board>> searchByCategoryId(@PathVariable("id") @Valid Long id) {
        if(categoryDao.selectById(id) == null) {
            throw new CategoryNotFoundException();
        }

        List<Board> results = this.categoryDao.selectByCategoryIdBoardList(id);

        return ResponseEntity.ok(results);
    }

}
