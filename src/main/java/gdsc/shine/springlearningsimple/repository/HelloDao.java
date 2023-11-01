package gdsc.shine.springlearningsimple.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HelloDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUserByName(String userName) {
        String sql = "INSERT INTO users(name) VALUES(?)";
        jdbcTemplate.update(sql, userName);
    }

    public int countByUserName(String userName) {
        String sql = "SELECT count(*) FROM users WHERE name = ? GROUP BY name";
        return jdbcTemplate.queryForObject(sql, Integer.class, userName);
    }
}