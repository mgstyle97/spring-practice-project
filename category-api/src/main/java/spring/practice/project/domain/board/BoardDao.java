package spring.practice.project.domain.board;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import spring.practice.project.domain.comment.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

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
        Board board;

        try {
            board = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM board WHERE id = ?",
                    this.rowMapper,
                    id
            );
        } catch (DataAccessException ex) {
            return null;
        }

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

    public List<Comment> selectByBoardId2CommentList(final Long id) {
        List<Comment> commentList = this.jdbcTemplate.query(
                "SELECT * FROM comment WHERE board_id = ?",
                (ResultSet rs, int rowNum) -> {
                    Comment comment = new Comment(
                            rs.getString("contents"),
                            rs.getBoolean("access"),
                            rs.getString("writer"),
                            rs.getLong("board_id")
                    );

                    comment.setId(rs.getLong("id"));
                    comment.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());

                    return comment;
                },
                id
        );

        return commentList;
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

    public void increseViews(final Long id) {
        this.jdbcTemplate.update(
                "UPDATE board SET views = views+1 WHERE id = ?",
                id
        );
    }

    public void update(final Board board) {
        this.jdbcTemplate.update(
                "UPDATE board SET title = ?, contents = ?, access = ?, mod_date = ? WHERE id = ?",
                board.getTitle(), board.getContents(), board.isAccess(), LocalDateTime.now(), board.getId()
        );
    }

    public void deleteById(final Long id) {
        this.jdbcTemplate.update(
                "DELETE FROM board WHERE id = ?",
                id
        );
    }

    public void deleteToBoardCategory(final Long boardId) {
        this.jdbcTemplate.update(
                "DELETE FROM board_category WHERE board_id = ?",
                boardId
        );
    }

}
