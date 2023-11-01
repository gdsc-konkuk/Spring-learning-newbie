package gdsc.shine.springlearningsimple.bean;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import gdsc.shine.springlearningsimple.domain.user.repository.UserRepository;

@SpringBootTest
public class BeanTest {

	@Autowired
	ApplicationContext ac;

	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {
		// 1. 조회: 호출할 때 마다 객체 생성
		UserRepository userRepository1 = ac.getBean("userRepository", UserRepository.class);

		// 2. 조회: 호출할 때 마다 객체 생성
		UserRepository userRepository2 = ac.getBean("userRepository", UserRepository.class);

		// 참조 값이 다른 것을 확인
		System.out.println("userRepository1 = " + userRepository1);
		System.out.println("userRepository2 = " + userRepository2);

		assertThat(userRepository1).isSameAs(userRepository2);
	}
}
