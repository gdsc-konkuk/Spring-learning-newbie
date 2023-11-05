package gdsc.shine.springlearningsimple;

import gdsc.shine.springlearningsimple.dao.HelloDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BeanTest {
    @Autowired
    ApplicationContext ac;

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // 1.조회: 호출할 때 마다 객체를 생성
        HelloDao helloDao1 = ac.getBean("helloDao", HelloDao.class);
        // 2.조회: 호출할 때 마다 객체를 생성
        HelloDao helloDao2 = ac.getBean("helloDao", HelloDao.class);
        System.out.println("hDao1 : " + helloDao1);
        System.out.println("hDao2 : " + helloDao2);

        assertThat(helloDao1).isSameAs(helloDao2);

    }
}
