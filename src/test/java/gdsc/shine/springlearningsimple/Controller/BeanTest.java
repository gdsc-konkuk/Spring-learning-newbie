package gdsc.shine.springlearningsimple.Controller;

import gdsc.shine.springlearningsimple.DAO.HelloDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BeanTest {
    @Autowired
    ApplicationContext ac;

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void HelloDao() {
        HelloDao helloDao1 = ac.getBean("helloDao", HelloDao.class);

        HelloDao helloDao2 = ac.getBean("helloDao", HelloDao.class);

        System.out.println("helloDao1 = " + helloDao1);

        System.out.println("helloDao2 = " + helloDao2);

        assertThat(helloDao1).isSameAs(helloDao2);
    }
}
