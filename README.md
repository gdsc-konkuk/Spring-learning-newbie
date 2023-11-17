### OOP의 특징

- Encapsulation - 캡슐화
- Inheritance - 상속
- Abstraction - 추상화
- Polymorphism - 다형성

### Solid 원칙

- **S**ingle Responsibility Principle - 단일 책임 원칙 = SRP
- **O**pen / Closed Principle - 개방 / 폐쇄 원칙 = OCP
- **L**iskov’s Substitution Principle - 리스코프 치환 원칙 = LSP
- **I**nterface Segregation Principle - 인터페이스 분리 원칙 = ISP
- **D**ependency Inversion Principle - 의존관계 역전 원칙 = DIP

### Spring Triangle

- Aspect Oriented Programming - 관점 지향 프로그래밍 = AOP
- Inversion of Control - 제어의 역전 = IoC
- Portable Service Abstraction - 추상화 서비스 = PSA

### Dependency Injection(DI)

[의존성(Dependency)이란?](https://mangkyu.tistory.com/226)  
출처: 망나니개발자

- Dependency(의존 관계)는 언제 발생하는가 ?

  객체의 세계에서 협력 = 필수적

  객체가 협력한다는 것 = 객체 간의 의존성이 존재

  ∴ **객체가 협력할 때 의존 관계 발생**

  의존성 = 파라미터 or 리턴값 or 지역 변수 등으로 다른 객체를 참조

- 객체들간의 의존 관계를 어떻게 연결하는 것이 좋을 것인가에 대한 고민의 결과

### 1. DI

#### 1-1. DI에 대해 알아보기 전에

- 다음 코드에서 DateMessageProviderTest에서 2개의 테스트가 모두 성공하도록 하는 방법 ?
- 2개의 테스트를 성공하기 힘들게 하는 원인 ?

```java
import java.util.Calendar;

public class DateMessageProvider {

	public String getDateMessage() {
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);

		if (hour < 12) {
			return "오전";
		}

		return "오후";
	}
}
```

```java
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class DateMessageProviderTest {
	@Test
	public void 오전() throws Exception {
		DateMessageProvider provider = new DateMessageProvider();
		assertThat(provider.getDateMessage(), is("오전"));
	}

	@Test
	public void 오후() throws Exception {
		DateMessageProvider provider = new DateMessageProvider();
		assertThat(provider.getDateMessage(), is("오후"));
	}
}
```

현재 `DateMessageProviderTest` 클래스에서 제공된 두 테스트 메서드 `오전()`, `오후()`는 `DateMessageProvider` 클래스의 `getDateMessage()` 메서드를
테스트합니다.  
해당 메서드는 시스템 시간에 따라 "오전" 또는 "오후"를 반환하는데, 테스트 환경에서는 이 조건을 동시에 만족시킬 수가 없습니다.  
오전에 실행하면 `오전()` 테스트는 통과하지만, `오후()` 테스트는 통과하지 못하고, 오후에 실행하면 이 반대가 될 것입니다.

#### 테스트 하기 쉬운 코드 ?

- 새로운 기능이 추가되었을 때 확장하기 쉬운 구조
- 기능이 변경되었을 때 기능을 변경하기 쉬운 구조
- 100% 일치하는 것은 아니지만 테스트하기 쉬운 코드의 경우 상당 부분 확장하기 좋고, 변경하기 좋은 구조를 가짐

#### 1-2. DI란 무엇인가 ?

- 객체들간의 의존 관계를 어떻게 연결하는 것이 좋을 것인가에 대한 고민의 결과
- DI 기반으로 프로그래밍을 하는 것 = 레고 블록을 조립하는 것과 같은 방식으로 프로그래밍이 가능해지는 것을 의미 -> 특정 인터페이스 규격만 맞추면 다양한 형태의 결과물 반환 가능 !

#### 1-3. Why DI ?

- Container 기반으로 객체를 관리할 때의 장점을 다음과 같이 설명

> The object created by Spring Container are cleaner and easier to use and they support reusability as well.
> These objects are easier to test also.

- cleaner + easier to use
- re-usability
- easier to test
- 애플리케이션 유지 보수 경험이 많지 않은 시점에 DI를 접할 경우 필요성을 느끼기 어려움 -> 경험이 많지 않은 상태에서 필요성을 느낄 수 있는 좋은 방법 = **테스트 코드 구현할 때**
- DI의 필요성을 제대로 느낄 수 있는 시점 = 애플리케이션을 오랜 기간 운영하면서 사용자의 요구사항을 변경하는 경험을 할 때 => 무엇인가 애플리케이션에 큰 변경이 발생하는 경험을 할 때 DI의 중요성을 느낄 수
  있음
- ∴ 경험이 많지 않은 상태에서 DI에 대한 필요성을 가슴 깊이 느끼고 싶다면 **테스트를 해라**

### 2. Bean과 Container

#### Servlet과 Servlet Container 관계

[ServletContainer ? DI Container ? Bean ? POJO ?](https://jojoldu.tistory.com/28) 참고
![Servlet_Container](..%2F..%2FLibrary%2FApplication%20Support%2FCleanShot%2Fmedia%2Fmedia_YyiVLPcK78%2FCleanShot%202023-11-09%20at%2013.14.12%402x.png)

- Servlet Container = Servlet의 라이프 사이클(생명 주기) 관리

#### Bean과 DI(or Spring) Container 관계

![Bean과_DI_Container_관계](..%2F..%2FLibrary%2FApplication%20Support%2FCleanShot%2Fmedia%2Fmedia_sj88tFf15c%2FCleanShot%202023-11-09%20at%2015.14.02.png)

- Spring Container가 관리하는 Object들이 **Bean**
- POJO = Plain Old Java Object -> 프레임워크 or 라이브러리의 종속으로부터 자유로운 클래스 의미
- Spring Container = 설정 파일(자바 설정 or XML)을 기반으로 POJO를 생성 -> 각 POJO간의 의존관계를 연결해주는 역할
- Spring = 컨테이너를 시작할 때 먼저 설정 파일을 통해 생성 -> DI할 Bean의 정보를 BeanDefinition으로 생성
- 설정 파일을 통해 읽은 BeanDefinition 정보를 바탕으로 -> Bean 인스턴스를 생성 -> Bean 간의 의존관계 연결

### 3. Spring 설정 파일

- 인스턴스 생성 및 라이프사이클 관리
- 인스턴스 간의 의존관계 설정

#### DI 적용

##### Singleton 패턴 기반

```java
public class QnaService {
	private static QnaService qnaService;

	private QuestionDao questionDao;
	private AnswerDao answerDao;

	private QnaService(QuestionDao questionDao, AnswerDao answerDao) {
		this.questionDao = questionDao;
		this.answerDao = answerDao;
	}

	public static QnaService getInstance(QuestionDao questionDao, AnswerDao answerDao) {
		if (qnaService == null) {
			qnaService = new QnaService(questionDao, answerDao);
		}
		return qnaService;
	}
}
```

```java
public class DeleteQuestionController extends AbstractController {
	private QnaService qnaService =
		QnaService.getInstance(QuestionDao.getInstance(), AnswerDao.getInstance());

    [...]
}

public class ApiDeleteQuestionController extends AbstractController {
	private QnaService qnaService =
		QnaService.getInstance(QuestionDao.getInstance(), AnswerDao.getInstance());

    [...]
}
```

##### Singleton 패턴 단점

- 생성자가 private이기 때문에 -> 상속 불가
- Singleton 패턴을 사용하는 경우 -> 객체 간의 의존관계를 직접 연결함으로써 테스트가 어려움

```java
public class QnaService {
	private QuestionDao questionDao;
	private AnswerDao answerDao;

	public QnaService(QuestionDao questionDao, AnswerDao answerDao) {
		this.questionDao = questionDao;
		this.answerDao = answerDao;
	}
}
```

```java
public class DeleteQuestionController extends AbstractController {
	private QnaService qnaService;

	public DeleteQuestionController(QnaService qnaService) {
		this.qnaService = qnaService;
	}
}

public class ApiDeleteQuestionController extends AbstractController {
	private QnaService qnaService;

	public ApiDeleteQuestionController(QnaService qnaService) {
		this.qnaService = qnaService;
	}
}
```

```java
public class LegacyHandlerMapping implements HandlerMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map mappings = new HashMap<>();

	public void initMapping() {
		QuestionDao questionDao = new JdbcQuestionDao();
		AnswerDao answerDao = new JdbcAnswerDao();
		QnaService qnaService = new QnaService(questionDao, answerDao);

		mappings.put("/", new HomeController(questionDao));
		mappings.put("/qna/show", new ShowQuestionController(questionDao, answerDao));
		mappings.put("/qna/form", new CreateFormQuestionController());
		mappings.put("/qna/create", new CreateQuestionController(questionDao));
		mappings.put("/qna/updateForm", new UpdateFormQuestionController(questionDao));
		mappings.put("/qna/update", new UpdateQuestionController(questionDao));
		mappings.put("/qna/delete", new DeleteQuestionController(qnaService));
		mappings.put("/api/qna/deleteQuestion", new ApiDeleteQuestionController(qnaService));
		mappings.put("/api/qna/list", new ApiListQuestionController(questionDao));
		mappings.put("/api/qna/addAnswer", new AddAnswerController(questionDao, answerDao));
		mappings.put("/api/qna/deleteAnswer", new DeleteAnswerController(answerDao));

		logger.info("Initialized Request Mapping!");
	}
}
```

![의존관계도_희망편](..%2F..%2FLibrary%2FApplication%20Support%2FCleanShot%2Fmedia%2Fmedia_FFUjlAC2Wq%2FCleanShot%202023-11-09%20at%2015.27.35.png)

이 정도는 설정파일로 관리가 가능할지도 모르지만 -> 다음 그림은 과연 ?

![의존관계도_절망편](..%2F..%2FLibrary%2FApplication%20Support%2FCleanShot%2Fmedia%2Fmedia_I7rjS8CK0d%2FCleanShot%202023-11-09%20at%2015.28.57.png)

> 자바 코드를 통해 인스턴스를 직접 생성하고 DI를 연결하는 것은 가능  
> But, 이 방식으로 구현하는 경우 자바 객체 간의 의존관계가 깊어지는 경우 꼬리에 꼬리를 물면서 인스턴스를 전달해야 하는 불편함이 존재  
> Spring 프레임워크 = 이 같은 단점을 보완하기 위해 **XML**, **애노테이션**과 같은 설정으로 빈(Bean) 간의 의존관계 해결

#### Spring 프레임워크의 DI

> Spring 프레임워크의 시작 단계에서는 XML을 사용했지만 요즘은 거의 사용 X  
> Config Java 파일로 관리 !!

##### Spring 설정이 XML → Java Config로 전환

```java

@Configuration
public class AppConfig {

	@Bean
	public PersonService personService(PersonDao personDao) {
		return new PersonService(personDao);
	}

	@Bean
	public PersonDao personDao() {
		return new PersonDao();
	}

}
```

##### Java Config 유용한 점

- 컴파일러 or IDE를 통한 타입 검증 가능
- 자동 완성과 같은 IDE 지원 기능을 최대한 이용 가능
- 이해하기 쉬움
- 복잡한 Bean 설정 or 초기화 작업을 손쉽게 가능

#### 의존관계 설정 방식

#### Setter Injection

```java
public class Person {
	private Ability ability;

	@Autowired
	pubilc

	void setAbility(Ability ability) {
		this.ability = ability;
	}
}
```

#### Constructor Injection

```java
public class Person {
	private Ability ability;

	@Autowired
	pubilc Person(Ability ability) {
		this.ability = ability;
	}
}
```

#### Field Injection

```java
public class Person {
	@Autowired
	private Ability ability;

        […]
}
```

위 3가지 방법 중 → **생성자 주입** 방식이 가장 보편적 + 안전

#### Spring Annotation Style Best Practices

- Bean 이름을 명확히 명시할 것 → `@Component("beanName")`
- `@Qualifier` 애노테이션 사용은 가급적 지양할 것
- `@ComponentScan` 설정 시 패키지 제한할 것
- [SPRING INJECTION WITH @RESOURCE, @AUTOWIRED AND @INJECT](https://www.sourceallies.com/2011/08/spring-injection-with-resource-and-autowired/)
  문서 확인할 것

## Week02 미션

### 1. 테스트 확인하기

- 테스트 메서드의 주석을 확인하여 → 프로덕션 코드에 추가할 내용 인지하기

> 계속 테스트 실패가 떠서 로그 살펴보니 사용하지 않는 `schema.sql`을 삭제했어야 함

### 2. Production 코드에 기능 구현하기

`@Component` 어노테이션 작성

### 3. 테스트 다시 수행

![componentScan()_테스트](..%2F..%2FLibrary%2FApplication%20Support%2FCleanShot%2Fmedia%2Fmedia_luC5zIFYia%2FCleanShot%202023-11-09%20at%2021.55.44.png)

`@Component` 어노테이션 작성 뒤 테스트 통과 확인

### 4. Context: Bean 설정 메타 정보(BeanDefinition)

Spring과 관련된 설정을 하기 위한 방법 2가지 → XML & Java
(2가지 방식 혼용 가능)  
이유: Spring이 설정 메타정보를 `BeanDefinition` 인터페이스를 통해 관리하기 때문

`Spring Application Context(스프링 애플리케이션 컨텍스트)`: 애플리케이션이 시작되면 `BeanDefinitionReader`라는 인터페이스의
구현체들을 통해 XML, Java, Properties 등으로 된 설정 정보들을 읽어 `BeanDefinition` 인터페이스의 객체로 설정 정보들을 관리  
XML을 위해서는 `XmlBeanDefinitionReader`, Java 설정을 위해서는 `AnnotationClassBeanDefinitionReader`, Properties 설정을
위해서는 `PropertiesBeanDefinitionReader` 등이 각각 사용됨 → 지난 미션 shine 글 참고

Application Context: 이 객체들을 바탕으로 Bean의 생성과 DI(의존성 주입) 등을 진행  
→ 그래서 Spring의 메타 정보 = 특정 파일 포켓 or 형식에 제한되거나 종속되지 않는 것  
심지어는 `RootBeanDefinition`과 같은 `BeanDefinition`의 구현체를 직접 생성해서 등록해줄 수도 있음

우리가 직접 `ApplicationContext`를 구현할 일은 없음 → But 그래도 어떠한 종류의 `ApplicationContext` 구현 클래스들이 있는지 살펴보자

#### StaticApplicationContext

Bean 설정 메타정보를 담은 `BeanDefinition` 객체를 직접 만들고, 코드를 통해 IoC에 등록하기 위해 사용되는 구현체  
`StaticApplicationContext`: Spring의 기능에 대한 학습 테스트를 진행할 때를 제외하고 실제로 사용 X  
나중에 Spring의 웹과 관련된 기능에 대해 학습 테스트를 작성하고 공부를 할 때에는 서브 클래스인 `StaticWebApplicationContext` 사용

#### GenericApplicationContext

가장 일반적인 구현 클래스로써 → 실무에서 사용될 모든 기능 갖추고 있음  
`StaticApplicationContext`와 달리 XML과 같이 외부에 있는 Bean 설정 메타 정보를 `BeanDefinitionReader`를 통해 읽어들여
`BeanDefinition`을 정의하기 위해 사용  
`BeanDefinitionReader`의 대표적인 구현체로는 `XmlBeanDefinitionReader`, `PropertiesBeanDefinitionReader` 등이 있는데,
`GenericApplicationContext`는 이러한 `Reader`들을 이용해 Bean을 등록

#### GenericXmlApplicationContext

코드 상에서 `GenericApplicationContext`를 사용하는 경우 → `XmlBeanDefinitionReader`를 내장하고 있는 `GenericXmlApplicationContext`를
사용하면 편리  
XML 파일로 설정을 만들고 Application Context에서 XML을 읽어 사용하는 코드를 작성할 때 적당

#### WebApplicationContext

Spring에서 가장 많이 사용되는 `ApplicationContext`로써 `ApplicationContext`를 확장한 인터페이스  
`WebApplicationContext` = 웹 환경에서 사용할 때 필요한 기능들이 추가됨  
다른 컨텍스트와 마찬가지로 `WebApplicationContext`도 XML 설정을 사용하도록 구현된 `WebXmlApplicationContext` 존재  
추가로 Java 어노테이션 설정을 이용하도록 구현된 `AnnotationConfigWebApplicationContext`도 존재

### 참고 레퍼런스 모음

#### scan
- [@Component](https://docs.spring.io/spring-framework/reference/core/beans/classpath-scanning.html#beans-stereotype-annotations)

#### DI
- [Dependencies](https://docs.spring.io/spring-framework/reference/core/beans/dependencies.html)
- [Constructor Injection](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html#beans-constructor-injection)
- [Setter Injection](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html#beans-setter-injection)
- [Field Injection](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired.html)

---
### ✏️ 과제 수행하며 공부한 것
`RootBeanDefinition`을 사용하여 → `ApplicationContext`에 직접 Bean을 등록하면 → 기본적으로 `singleton`  
Spring 프레임워크에서 Bean의 스코프(scope) = 기본적으로 `singleton`  
