package spring.practice.project.domain.board;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BoardDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Board> rowMapper = (ResultSet rs, int rowNum) -> {
        Board board = new Board(
                rs.getString("title"),
                rs.getString("contents"),
                rs.getBoolean("access"),
                rs.getString("writer")
        );
        board.setId(rs.getLong("id"));
        board.setRegDate(
                rs
                .getTimestamp("reg_date")
                .toLocalDateTime()
        );
        board.setViews(rs.getLong("views"));

        return board;
    };

    @Autowired
    public BoardDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Board selectById(final Long id) {
        Board board = this.jdbcTemplate.queryForObject(
                "SELECT * FROM board WHERE id = ?",
                this.rowMapper,
                id
        );

        return board;
    }

    public Board selectByTitle(final String title) {
        Board board = this.jdbcTemplate.queryForObject(
                "SELECT * FROM board WHERE title = ?",
                this.rowMapper,
                title
        );

        return board;
    }

    public Long insert(final Board board) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "INSERT INTO board(title, contents, access, writer) VALUES(?, ?, ?, ?)",
                        new String[]{"id"}
                );

                pstmt.setString(1, board.getTitle());
                pstmt.setString(2, board.getContents());
                pstmt.setBoolean(3, board.isAccess());
                pstmt.setString(4, board.getWriter());

                return pstmt;
            }
        }, keyHolder);

        Number keyValue = keyHolder.getKey();
        return keyValue.longValue();
    }

    public void insertToBoardCategory(final Long boardId, final Long categoryId) {
        this.jdbcTemplate.update(
                "INSERT INTO board_category(board_id, category_id) VALUES(?, ?)",
                boardId, categoryId
        );
    }

    public void update(final Board board) {
        this.jdbcTemplate.update(
                "UPDATE board SET title = ?, contents = ?, access = ?, views = ? WHERE id = ?",
                board.getTitle(), board.getContents(), board.isAccess(), board.getViews(), board.getId()
        );
    }

    public void deleteById(final Long id) {
        this.jdbcTemplate.update(
                "DELETE FROM board WHERE id = ?",
                id
        );
    }

}
