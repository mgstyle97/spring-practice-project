package spring.practice.project.domain.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.practice.project.domain.board.Board;
import spring.practice.project.domain.board.BoardDao;
import spring.practice.project.domain.comment.Comment;
import spring.practice.project.domain.comment.CommentDao;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.user.User;

import java.util.List;

@Component
public class BoardDeleteService {

    private BoardDao boardDao;
    private CommentDao commentDao;

    @Autowired
    public void setBoardDao(final BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Autowired
    public void setCommentDao(final CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Transactional
    public void delete(final Long id, final User user) {
        Board board = this.boardDao.selectById(id);
        if(board == null || !board.isWriter(user.getId())) {
            throw new InvalidApproachException();
        }

        List<Comment> commentList = this.boardDao.selectByBoardId2CommentList(id);
        for(Comment comment : commentList) {
            this.commentDao.delete(comment.getId());
        }
        this.boardDao.deleteToBoardCategory(id);
        this.boardDao.deleteById(id);
    }

}
