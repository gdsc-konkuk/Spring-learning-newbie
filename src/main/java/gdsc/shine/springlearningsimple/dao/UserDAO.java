package gdsc.shine.springlearningsimple.dao;

import gdsc.shine.springlearningsimple.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Slf4j
@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //[2번 문제]
    public void insertUserByName(String userName) {
        String sql = "INSERT INTO users(name) VALUES(?)";
        jdbcTemplate.update(sql, userName);
    }

    //[2번 문제]
    public int countByUserName(String userName) {
        String sql = "SELECT COUNT(*) FROM users WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userName);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setCount(rs.getLong("count"));
            return user;
        }; }

}