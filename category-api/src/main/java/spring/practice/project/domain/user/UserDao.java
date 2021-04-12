package spring.practice.project.domain.user;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class UserDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> rowMapper = (ResultSet rs, int rowNum) -> {
        User user = new User(
                rs.getString("id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("nick"),
                rs.getTimestamp("reg_date").toLocalDateTime(),
                rs.getBoolean("is_admin")
        );
        return user;
    };

    @Autowired
    public void setJdbcTemplate(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User selectById(final String id) {
        User user;

        try {
            user = jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE id = ?",
                    rowMapper,
                    id
            );
        } catch (DataAccessException ex) {
            return null;
        }

        return user;
    }

    public User selectByNick(final String nick) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE nick = ?",
                    rowMapper,
                    nick
            );
        } catch (DataAccessException ex) {
            return null;
        }

        return user;
    }

    public void insert(final User user) {
        jdbcTemplate.update(
                "INSERT INTO users(id, password, name, nick, is_admin) VALUES(?, ?, ?, ?, ?)",
                user.getId(), user.getPassword(), user.getName(),
                user.getNick(), user.isAdmin()
        );
    }

    public void update(final User user) {
        jdbcTemplate.update(
                "UPDATE users SET name = ?, password = ?, nick = ?, modified_time = ? WHERE id = ?",
                user.getName(), user.getPassword(), user.getNick(), user.getModifiedTime(), user.getId()
        );
    }

    public void deleteById(final String id) {
        jdbcTemplate.update(
                "DELETE FROM users WHERE id = ?",
                id
        );
    }

}
