package gdsc.shine.springlearningsimple.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {
    private final JdbcTemplate jdbcTemplate;

    public HelloDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUserByName(String userName) {
        String sql = "INSERT INTO users (name) VALUES (?)";
        jdbcTemplate.update(sql, userName);
    }

    public Integer countByUserName(String userName) {
        String sql = "SELECT COUNT(*) FROM users WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userName);
    }
}
