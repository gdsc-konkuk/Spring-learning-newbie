package gdsc.shine.springlearningsimple.domain.user.domain;

import lombok.Getter;

@Getter
public class User {

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
