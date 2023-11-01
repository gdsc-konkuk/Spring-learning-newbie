package gdsc.shine.springlearningsimple.domain.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {

	private final JdbcTemplate jdbcTemplate;

	public HelloDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
