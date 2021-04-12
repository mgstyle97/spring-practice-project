package spring.practice.project.domain.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.practice.project.domain.board.Board;
import spring.practice.project.domain.category.CategoryDao;
import spring.practice.project.domain.global.exception.NotFoundException;
import spring.practice.project.domain.user.User;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
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
    public ResponseEntity<List<Board>> searchByCategoryId(@PathVariable("id") @Valid final Long id,
                                                          final HttpSession session) {
        if(categoryDao.selectById(id) == null) {
            throw new NotFoundException();
        }
        User user = (User) session.getAttribute("user");

        List<Board> boardList = this.categoryDao.selectByCategoryIdBoardList(id);
        List<Board> results = organizeBoardListByAccess(user, boardList);


        return ResponseEntity.ok(results);
    }

    private List<Board> organizeBoardListByAccess(final User user, final List<Board> boardList) {
        List<Board> results = new ArrayList<>();

        if(user == null) {
            for (Board board : boardList) {
                if(!board.isAccess()) {
                    results.add(board);
                }
            }
        } else {
            for (Board board : boardList) {
                if(board.isAccess() && !board.getWriter().equals(user.getId())) {
                    continue;
                }
                results.add(board);
            }
        }

        return results;
    }

}
