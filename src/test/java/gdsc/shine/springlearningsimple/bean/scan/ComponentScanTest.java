package gdsc.shine.springlearningsimple.bean.scan;

import gdsc.shine.springlearningsimple.SpringLearningSimpleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentScanTest {

    /**
     * TODO: bean > scan 패키지 내에 있는 클래스를 스프링 빈으로 등록하기
     */
    @Test
    void componentScan() {
        ApplicationContext context = getApplicationContext();
        LineDao dao = context.getBean("lineDao", LineDao.class);
        assertThat(dao).isNotNull();

        LineService service = context.getBean("lineService", LineService.class);
        assertThat(service).isNotNull();
    }

    /**
     * SpringLearningSimpleApplication > @SpringBootApplication 설정을 통해 이미 ComponentScan 설정되어있음
     */
    private ApplicationContext getApplicationContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringLearningSimpleApplication.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        return context;
    }
}
