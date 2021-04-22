package spring.practice.project.domain.comment;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import spring.practice.project.domain.global.exception.NotFoundException;

import java.sql.ResultSet;

@Component
public class CommentDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Comment> rowMapper = (ResultSet rs, int rowNum) -> {
        Comment comment = new Comment(
                rs.getString("contents"),
                rs.getBoolean("access"),
                rs.getString("writer"),
                rs.getLong("board_id")
        );
        comment.setId(rs.getLong("id"));
        comment.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());

        return comment;
    };

    @Autowired
    public CommentDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Comment selectById(final Long id) {
        Comment comment;
        try {
            comment = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM comment WHERE id = ?",
                    this.rowMapper,
                    id
            );
        } catch (DataAccessException ex) {
            return null;
        }

        return comment;
    }

    public void insert(final Comment comment) {
        this.jdbcTemplate.update(
                "INSERT INTO comment(contents, access, writer, board_id) VALUES(?, ?, ?, ?)",
                comment.getContents(), comment.isAccess(),
                comment.getWriter(), comment.getBoardId()
        );
    }

    public void update(final Comment comment) {
        this.jdbcTemplate.update(
                "UPDATE comment SET contents = ?, mod_date = ?, access = ? WHERE id = ?",
                comment.getContents(), comment.getModDate(), comment.isAccess(), comment.getId()
        );
    }

    public void delete(final Long id) {
        this.jdbcTemplate.update(
                "DELETE FROM comment WHERE id = ?",
                id
        );
    }

}
