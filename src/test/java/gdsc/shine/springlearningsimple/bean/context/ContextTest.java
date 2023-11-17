package gdsc.shine.springlearningsimple.bean.context;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ContextTest {

    @Test
    void test1() {
        // TODO : 미션에 있던 학습 테스트를 위해 사용되는 IoC 컨테이너 생성
        StaticApplicationContext ac = new StaticApplicationContext();

        // TODO : Hello 클래스를 싱글톤 빈으로 컨테이너에 등록
        ac.registerBeanDefinition("Hello", new RootBeanDefinition(Hello.class));

        // TODO : IoC 컨테이너에서 Hello 찾아오기
        Object targetBean = ac.getBean("Hello", Hello.class);

        assertThat(targetBean).isNotNull();
    }

    @Test
    void test2() {
        // TODO : test1의 4단계 그대로 진행하되, 빈 메타정보를 shine1이라는 이름으로 컨테이너에 등록
        StaticApplicationContext context = new StaticApplicationContext();
        context.registerBeanDefinition("shine1", new RootBeanDefinition(Shine.class));

        // 비어있는 Shine정보를 담은 오브젝트 생성
        BeanDefinition shineDef = new RootBeanDefinition(Shine.class);
        // 빈 프로퍼티 설정
        shineDef.getPropertyValues().addPropertyValue("name", "Gdsc Konkuk");

        // TODO : 빈 메타정보를 hello2 라는 이름으로 컨테이너에 등록
        context.registerBeanDefinition("hello2", shineDef);

        // TODO : shine1, shine2 빈을 컨테이너에서 가져오기
        Shine shine1 = context.getBean("shine1", Shine.class);
        Shine shine2 = context.getBean("hello2", Shine.class);

        // TODO : 테스트 통과시키기
        assertThat(shine1).isNotNull();
        assertThat(shine2).isNotNull();
        assertThat(shine2.sayHello()).isEqualTo("Hello Gdsc Konkuk");

        assertThat(shine1).isNotSameAs(shine2);
        assertThat(context.getBeanFactory().getBeanDefinitionCount()).isEqualTo(2);
    }

    @Test
    void test3() {
        StaticApplicationContext context = new StaticApplicationContext();
        context.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));

        BeanDefinition shineDef = new RootBeanDefinition(Shine.class);
        shineDef.getPropertyValues().addPropertyValue("name", "Gdsc Konkuk");

        // TODO : 아이디가 printer 인 빈을 찾아서 shineDef의 printer 프로퍼티에 DI 시키기
        shineDef.getPropertyValues()
                .addPropertyValue("printer", context.getBean("printer", StringPrinter.class));

        // TODO : shine 빈을 컨테이너에 등록시키기
        context.registerBeanDefinition("shine", shineDef);

        Shine shine = context.getBean("shine", Shine.class);
        shine.print();

        assertThat(context.getBean("printer").toString()).isEqualTo("Hello Gdsc Konkuk");
    }
}
