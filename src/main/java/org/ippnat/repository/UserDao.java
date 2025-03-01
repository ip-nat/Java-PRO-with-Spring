package org.ippnat.repository;

import org.ippnat.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRow = (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"));

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(String username) {
        if (isUsernameExists(username)) {
            System.out.println("Пользователь '" + username + "' уже существует");
            return;
        }
        jdbcTemplate.update("INSERT INTO users (username) VALUES (?)", username);
    }

    public Optional<User> get(Long id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id = ?", userRow, id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.getFirst());
    }

    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users", userRow);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

}
