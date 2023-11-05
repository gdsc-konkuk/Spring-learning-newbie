package gdsc.shine.springlearningsimple.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class HelloDao {
    private final JdbcTemplate jdbcTemplate;

    public HelloDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUserName(String userName) {
        String sql = "insert into USERS (NAME) values (?)";
        jdbcTemplate.update(sql, userName);
    }

    public Optional<Integer> countByUserName(String userName) {
        String sql = "select COUNT(*) from USERS where NAME = (?)";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, Integer.TYPE, userName));
    }
}
