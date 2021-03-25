package spring.practice.project.domain.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.practice.project.domain.comment.Comment;
import spring.practice.project.domain.comment.CommentDao;
import spring.practice.project.domain.comment.command.CommentRegisterCommand;

@Component
public class CommentRegisterService {

    private CommentDao dao;

    @Autowired
    public void setDao(final CommentDao dao) {
        this.dao = dao;
    }

    public void regist(final CommentRegisterCommand command) {
        Comment comment = new Comment(
                command.getContents(), command.isAccess(),
                command.getWriter(), command.getBoardId()
        );

        this.dao.insert(comment);
    }

}
