package spring.practice.project.domain.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.practice.project.domain.board.BoardDao;
import spring.practice.project.domain.comment.Comment;
import spring.practice.project.domain.comment.CommentDao;
import spring.practice.project.domain.comment.command.CommentRegisterCommand;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.user.UserDao;

@Component
public class CommentRegisterService {

    private CommentDao commentDao;
    private BoardDao boardDao;
    private UserDao userDao;

    @Autowired
    public void setCommentDao(final CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Autowired
    public void setBoardDao(final BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Autowired
    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }

    public void regist(final CommentRegisterCommand command) {
        if ((userDao.selectById(command.getWriter()) == null) ||
                (boardDao.selectById(command.getBoardId()) == null)) {
            throw new InvalidApproachException();
        }

        Comment comment = new Comment(
                command.getContents(), command.isAccess(),
                command.getWriter(), command.getBoardId()
        );

        this.commentDao.insert(comment);
    }

}
