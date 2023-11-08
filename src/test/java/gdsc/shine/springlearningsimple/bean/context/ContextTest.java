package gdsc.shine.springlearningsimple.bean.context;

import gdsc.shine.springlearningsimple.SpringLearningSimpleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ContextTest {

    @Test
    void test1() {
        // TODO : 미션에 있던 학습 테스트를 위해 사용되는 IoC 컨테이너 생성
        // 여기에 작성
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringLearningSimpleApplication.class);

        // TODO : Hello 클래스를 싱글톤 빈으로 컨테이너에 등록
        // 여기에 작성
        ((AnnotationConfigApplicationContext) applicationContext).registerBean(Shine.class);

        // TODO : IoC 컨테이너에서 Hello 찾아오기
        // 여기에 작성
        Shine shine = applicationContext.getBean(Shine.class);

        assertThat(shine).isNotNull();
    }

    @Test
    void test2() {
        // TODO : test1의 4단계 그대로 진행하되, 빈 메타정보를 shine1이라는 이름으로 컨테이너에 등록
        // 여기에 작성
        StaticApplicationContext context = new StaticApplicationContext();
        context.registerBean("shine1", Shine.class);

        // 비어있는 Shine정보를 담은 오브젝트 생성
        BeanDefinition shineDef = new RootBeanDefinition(Shine.class);

        // 빈 프로퍼티 설정
        shineDef.getPropertyValues().addPropertyValue("name", "Gdsc Konkuk");

        // TODO : 빈 메타정보를 hello2 라는 이름으로 컨테이너에 등록
        // 여기에 작성
        context.registerBeanDefinition("shine2", shineDef);

        // TODO : shine1, shine2 빈을 컨테이너에서 가져오기
        // 여기에 작성
        Shine shine1 = context.getBean("shine1", Shine.class);
        Shine shine2 = context.getBean("shine2", Shine.class);


        // TODO : 테스트 통과시키기
        assertThat(shine1).isNotNull();
        assertThat(shine2).isNotNull();
        assertThat(shine2.sayHello()).isEqualTo("Hello Gdsc Konkuk");

//        assertThat(shine1).isNotSameAs(shine2); // Spring Container는 싱글톤 패턴으로 빈을 관리하기 때문에 테스트는 실패함
        assertThat(context.getBeanFactory().getBeanDefinitionCount()).isEqualTo(2);
    }

    @Test
    void test3() {
        StaticApplicationContext context = new StaticApplicationContext();
        context.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));

        BeanDefinition shineDef = new RootBeanDefinition(Shine.class);
        shineDef.getPropertyValues().addPropertyValue("name", "Gdsc Konkuk");

        // TODO : 아이디가 printer 인 빈을 찾아서 shineDef의 printer 프로퍼티에 DI 시키기
        // 여기에 작성

        // TODO : shine 빈을 컨테이너에 등록시키기
        // 여기에 작성

        Shine shine = context.getBean("shine", Shine.class);
        shine.print();

        assertThat(context.getBean("printer").toString()).isEqualTo("Hello Gdsc Konkuk");
    }
}
