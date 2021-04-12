package spring.practice.project.domain.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.practice.project.domain.board.Board;
import spring.practice.project.domain.board.BoardDao;
import spring.practice.project.domain.board.command.BoardModifyCommand;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.global.exception.NotFoundException;
import spring.practice.project.domain.user.User;

@Component
public class BoardModifyService {

    private BoardDao dao;

    @Autowired
    public void setDao(final BoardDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void modify(final BoardModifyCommand command, final User user) {
        Board board = this.dao.selectById(command.getId());
        if(board == null) {
            throw new NotFoundException();
        }
        if(user == null || board.isWriter(user.getId())) {
            throw new InvalidApproachException();
        }

        Board updatedBoard = updateBoardByCommand(board, command);
        this.dao.update(updatedBoard);
    }

    private Board updateBoardByCommand(final Board board, final BoardModifyCommand command) {
        if(command.getTitle() != null &&
                !board.getTitle().equals(command.getTitle())) {
            board.setTitle(command.getTitle());
        }
        if(command.getContents() != null &&
                !board.getContents().equals(command.getContents())) {
            board.setContents(command.getContents());
        }
        if(board.isAccess() != command.isAccess()) {
            board.setAccess(command.isAccess());
        }

        return board;
    }

}
