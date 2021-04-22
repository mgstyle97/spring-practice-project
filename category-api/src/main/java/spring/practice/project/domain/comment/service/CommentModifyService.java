package spring.practice.project.domain.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.practice.project.domain.board.BoardDao;
import spring.practice.project.domain.comment.Comment;
import spring.practice.project.domain.comment.CommentDao;
import spring.practice.project.domain.comment.command.CommentModifyCommand;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.global.exception.NotFoundException;

import java.time.LocalDateTime;

@Component
public class CommentModifyService {

    private CommentDao commentDao;
    private BoardDao boardDao;

    @Autowired
    public void setCommentDao(final CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Autowired
    public void setBoardDao(final BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void modify(final CommentModifyCommand command) {
        Comment comment = commentDao.selectById(command.getId());
        if ((comment == null) ||
                (boardDao.selectById(command.getBoardId())) == null) {
            throw new NotFoundException();
        }
        if (!comment.getWriter().equals(command.getWriter())) {
            throw new InvalidApproachException();
        }

        Comment updateComment = new Comment(
                command.getContents(), command.isAccess(),
                command.getWriter(), command.getBoardId()
        );
        updateComment.setId(command.getId());
        updateComment.setModDate(LocalDateTime.now());

        commentDao.update(updateComment);

    }

}
