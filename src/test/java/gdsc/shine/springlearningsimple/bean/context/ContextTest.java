package gdsc.shine.springlearningsimple.bean.context;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

public class ContextTest {

	@Test
	void test1() {
		// TODO : 미션에 있던 학습 테스트를 위해 사용되는 IoC 컨테이너 생성 [Done]
		StaticApplicationContext applicationContext = new StaticApplicationContext();

		// TODO : Shine 클래스를 싱글톤 빈으로 컨테이너에 등록 [Done]
		BeanDefinition shineDefinition = new RootBeanDefinition(Shine.class);
		applicationContext.registerBeanDefinition("shine", shineDefinition);

		// TODO : IoC 컨테이너에서 Shine 찾아오기 [Done]
		Shine shine = (Shine)applicationContext.getBean("shine");

		assertThat(shine).isNotNull();
	}

	@Test
	void test2() {
		// TODO : test1의 4단계 그대로 진행하되, 빈 메타정보를 shine1이라는 이름으로 컨테이너에 등록 [Done]
		StaticApplicationContext applicationContext = new StaticApplicationContext();

		BeanDefinition shineDefinition = new RootBeanDefinition(Shine.class);
		applicationContext.registerBeanDefinition("shine1", shineDefinition);

		// 비어있는 Shine정보를 담은 오브젝트 생성
		BeanDefinition shineEmptyDefinition = new RootBeanDefinition(Shine.class);

		// 빈 프로퍼티 설정
		shineEmptyDefinition.getPropertyValues().addPropertyValue("name", "Gdsc Konkuk");

		// TODO : 빈 메타정보를 shine2 라는 이름으로 컨테이너에 등록 [Done]
		applicationContext.registerBeanDefinition("shine2", shineEmptyDefinition);

		// TODO : shine1, shine2 빈을 컨테이너에서 가져오기 [Done]
		Shine shine1 = (Shine)applicationContext.getBean("shine1");
		Shine shine2 = (Shine)applicationContext.getBean("shine2");

		// TODO : 테스트 통과시키기 [Done]
		assertThat(shine1).isNotNull();
		assertThat(shine2).isNotNull();
		assertThat(shine2.sayHello()).isEqualTo("Hello Gdsc Konkuk");

		assertThat(shine1).isNotSameAs(shine2);
		assertThat(applicationContext.getBeanFactory().getBeanDefinitionCount()).isEqualTo(2);
	}

	@Test
	void test3() {
		StaticApplicationContext applicationContext = new StaticApplicationContext();
		applicationContext.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));

		BeanDefinition shineDefinition = new RootBeanDefinition(Shine.class);
		shineDefinition.getPropertyValues().addPropertyValue("name", "Gdsc Konkuk");

		// TODO : 아이디가 printer 인 빈을 찾아서 shineDefinition의 printer 프로퍼티에 DI 시키기 [Done]
		shineDefinition.getPropertyValues().addPropertyValue("printer", applicationContext.getBean("printer"));

		// TODO : shine 빈을 컨테이너에 등록시키기 [Done]
		applicationContext.registerBeanDefinition("shine", shineDefinition);

		Shine shine = applicationContext.getBean("shine", Shine.class);
		shine.print();

		assertThat(applicationContext.getBean("printer").toString()).isEqualTo("Hello Gdsc Konkuk");
	}
}
