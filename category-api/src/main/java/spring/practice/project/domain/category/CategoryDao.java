package spring.practice.project.domain.category;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import spring.practice.project.domain.board.Board;
import spring.practice.project.domain.NotFoundException;

import java.sql.ResultSet;
import java.util.List;

@Component
public class CategoryDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Category> rowMapper = (ResultSet rs, int rowNum) -> {
        Category category = new Category(
                rs.getString("title"),
                rs.getString("info")
        );
        category.setId(rs.getLong("id"));

        return category;
    };

    @Autowired
    public CategoryDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Category selectById(final Long id) {
        Category category;

        try {
            category = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM category WHERE id = ?",
                    this.rowMapper,
                    id
            );
        } catch (DataAccessException ex) {
            throw new NotFoundException();
        }

        return category;
    }

    public Category selectByTitle(final String title) {
        Category category;

        try {
            category = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM category WHERE title = ?",
                    this.rowMapper,
                    title
            );
        } catch (DataAccessException ex) {
            throw new NotFoundException();
        }

        return category;
    }

    public List<Board> selectByCategoryIdBoardList(final Long id) {
        List<Board> results = this.jdbcTemplate.query(
                "SELECT *\n" +
                        "FROM (\n" +
                        "\tSELECT board_id\n" +
                        "    FROM board_category\n" +
                        "    WHERE category_id = ?\n" +
                        ")BI, board\n" +
                        "WHERE board.id = BI.board_id\n" +
                        "ORDER BY board.views DESC",
                (ResultSet rs, int rowNum) -> {
                    Board board = new Board(
                            rs.getString("title"),
                            rs.getString("contents"),
                            rs.getBoolean("access"),
                            rs.getString("writer")
                    );
                    board.setId(rs.getLong("id"));
                    board.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
                    board.setViews(rs.getLong("views"));

                    return board;
                }, id
        );

        return results;
    }

    public void insert(final Category category) {
        this.jdbcTemplate.update(
                "INSERT INTO category(title, info) VALUES(?, ?)",
                category.getTitle(), category.getInfo()
        );
    }

    public void update(final Category category) {
        this.jdbcTemplate.update(
                "UPDATE category SET info = ? WHERE title = ?",
                category.getInfo(), category.getTitle()
        );
    }

    public void deleteByTitle(final String title) {
        this.jdbcTemplate.update(
                "DELETE FROM category WHERE title = ?",
                title
        );
    }

}