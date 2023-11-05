package gdsc.shine.springlearningsimple.domain.user.repository;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private final JdbcTemplate jdbcTemplate;

	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insertUserByName(String userName) {
		String sql = "INSERT INTO users (name) VALUES (?)";
		jdbcTemplate.update(sql, userName);
	}

	public int countByUserName(String userName) {
		String sql = "SELECT COUNT(*) FROM users WHERE name = ?";
		return Optional.ofNullable(jdbcTemplate.queryForObject(sql, Integer.class, userName)).orElse(0);
	}
}
