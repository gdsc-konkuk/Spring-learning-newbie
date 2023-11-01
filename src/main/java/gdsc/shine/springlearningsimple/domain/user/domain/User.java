package gdsc.shine.springlearningsimple.domain.user.domain;

import org.springframework.data.annotation.Id;

import lombok.Getter;

@Getter
public class User {

	@Id
	private Long id;

	private String name;

	private User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static User of(Long id, String name) {
		return new User(id, name);
	}

}
