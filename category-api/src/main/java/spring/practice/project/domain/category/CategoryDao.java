package spring.practice.project.domain.category;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

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

    public Category selectByTitle(final String title) {
        Category category = this.jdbcTemplate.queryForObject(
                "SELECT * FROM category WHERE title = ?",
                this.rowMapper,
                title
        );

        return category;
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
