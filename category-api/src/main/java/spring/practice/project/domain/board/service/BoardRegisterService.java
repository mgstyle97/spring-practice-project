package spring.practice.project.domain.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.practice.project.domain.board.Board;
import spring.practice.project.domain.board.BoardDao;
import spring.practice.project.domain.board.command.BoardRegisterCommand;

@Component
public class BoardRegisterService {

    private BoardDao dao;

    @Autowired
    public void setDao(final BoardDao dao) {
        this.dao = dao;
    }

    public BoardDao getDao() {
        return this.dao;
    }

    @Transactional
    public void register(final BoardRegisterCommand command) {

        Board board = new Board(
                command.getTitle(), command.getContents(),
                command.isAccess(), command.getWriter()
        );
        Long boardId = dao.insert(board);

        if(command.getCategoryId() == null) {
            command.setCategoryId(Long.valueOf(1));
        }

        dao.insertToBoardCategory(boardId, command.getCategoryId());
    }

}
