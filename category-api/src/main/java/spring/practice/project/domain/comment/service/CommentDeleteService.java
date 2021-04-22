package spring.practice.project.domain.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.practice.project.domain.comment.Comment;
import spring.practice.project.domain.comment.CommentDao;
import spring.practice.project.domain.comment.command.CommentDeleteCommand;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.user.UserDao;

@Component
public class CommentDeleteService {

    private CommentDao commentDao;

    @Autowired
    public void setCommentDao(final CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void delete(final CommentDeleteCommand command) {
        Comment comment = commentDao.selectById(command.getId());
        if ((comment == null) ||
                !(comment.getWriter().equals(command.getWriter()))) {
            throw new InvalidApproachException();
        }

        commentDao.delete(command.getId());
    }

}
