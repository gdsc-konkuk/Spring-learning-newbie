package gdsc.shine.springlearningsimple.domain.user.dao;

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

}
