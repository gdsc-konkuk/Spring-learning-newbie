# Shine의 Spring 학습 미션

## 🗓️ Week01

### ✏️ 학습 목표

- Query String
- 기초 Spring 앱 실행 방법
- `@RestController` 어노테이션
- 기본 DB 접근 + JDBC 기초
- **Spring Bean 맛보기**

### 📝 답변

#### 2-2) `@RestController` 어노테이션 살펴보기

1. Target의 의미는 ?  
   👉 `@Target` 어노테이션은 해당 어노테이션을 어디에 적용할 수 있는지 지정하는 역할을 수행합니다. `@Target`에 설정할 수 있는 값은 `ElementType` 열거 타입의 상수들입니다. 예를
   들어, `@Target(ElementType.METHOD)`는 해당 어노테이션을 메소드 단위에만 적용할 수 있다는 것을
   의미하고, `@Target({ElementType.METHOD, ElementType.FIELD})`와 같이 배열 형태로 여러 `ElementType` 상수도 지정할 수 있습니다. 만약 `@Target`
   어노테이션이 없는 경우에는 모든 `ElementType`에 적용 가능합니다.  
   `@RestController` 어노테이션에 적용된 `@Target` 어노테이션을 살펴보면 `@Target(ElementType.TYPE)`이라고 설정돼있습니다. 따라서 `@RestController`
   어노테이션은 클래스, 인터페이스, 어노테이션 타입 또는 열거형에만 적용시킬 수 있습니다.
2. Retention의 의미는 ?  
   👉 `@Retention` 어노테이션은 Java에서 다른 어노테이션의 라이프 사이클, 즉 해당 어노테이션이 언제까지 유지되어야 하는지를 지정하는 데 사용됩니다. Java 컴파일러와 JVM에게 해당 어노테이션의
   처리 방법을 알려주는 것이라고 할 수 있습니다.  
   `@Retention` 어노테이션에는 `RetentionPolicy`라는 `enum` 타입의 value가 설정되며 3가지 값을 가질 수 있게 됩니다.

- `SOURCE`: 어노테이션이 소스 파일에서만 유지되고 컴파일 과정에서는 제거됩니다.  
  ex) `@Override`
- `CLASS`(default): 어노테이션이 컴파일 된 클래스 파일에 포함되지만 런타임에는 JVM에 의해 유지되지 않습니다.
- `RUNTIME`: 어노테이션이 컴파일 된 클래스 파일에 포함되고, 런타임에 JVM에 의해 유지되어 리플렉션을 통해 액세스 가능합니다.  
  ex) `@Deprecated`

3. Controller의 의미는 ?  
   👉 `@Controller` 어노테이션은 Spring MVC에서 컨트롤러(Controller)를 정의할 때 사용되며, 해당 클래스를 컨트롤러로 선언하고 스프링의 웹 애플리케이션 컨텍스트에 `Bean`으로
   등록합니다. 컨트롤러는 클라이언트의 요청을 처리하고, 모델과 뷰를 조작하여 응답을 생성하는 역할을 수행합니다.
4. ReponseBody의 의미는 ?  
   👉 `@ResponseBody` 어노테이션은 Spring MVC에서 핸들러 메소드의 반환값을 HTTP 응답 본문(Body)에 직접 쓰도록 지정하는 역할을 수행합니다. 즉, 뷰(View)를 통하지 않고 HTTP
   응답 데이터를 직접 생성할 때 사용합니다.  
   `@RestController` 어노테이션에 내부적으로 `@ResponseBody` 어노테이션이 적용되어 있기 때문에 `@RestController` 어노테이션이 붙은 컨트롤러들의 모든 핸들러 메소드는
   기본적으로 `@ResponseBody`가 적용된 것 처럼 동작합니다.

#### 3-1) Dao 작성해보기

1. 어떤 어노테이션을 추가해야 할까 ?  
   👉 `@Repository` 어노테이션을 사용하여 해당 클래스를 Spring 컨테이너에 Bean으로 등록
2. 유저를 어떻게 추가할까 ?  
   👉 `insertUserByName` 메소드에서 `update` 메소드를 사용하여 파라미터를 통해 넘겨 받은 유저 이름으로 데이터(유저) 삽입
3. 유저의 수를 어떻게 알아낼까 ?  
   👉 `countByUserName` 메소드 내부에서 `queryForObject` 메소드를 사용하여 파라미터로 넘겨 받은 유저의 이름을 통해 특정 유저의 방문 회수 조회

#### 3-2) Controller에 Dao 주입하기

다음 코드의 문제점

```java

@RestController
public class HelloController {

	private HelloDao helloDao;

	public HelloController(HelloDao helloDao) {
		this.helloDao = helloDao;
	}

	// 생략 ...
}
```

👉 위 코드는 `final` 키워드가 부재한다는 점에서 많은 문제를 야기할 수 있습니다.  
`final` 키워드를 사용하면 해당 필드가 한 번 초기화되면 더 이상 변경되지 않는다는 것을 보장하는데, 위의 코드는 `final` 키워드가 없으므로 `helloDao` 필드의 참조가 변경될 가능성이 있으며 이는
곧 버그로 이어집니다.  
또한 멀티 스레드 환경에서 `final` 키워드가 적용되지 않은 필드는 다른 스레드에서 해당 값을 변경할 수 있기 때문에 동시성 문제가 발생할 가능성이 높습니다.

### ⚙️ 기능 요구 사항

- [X] `http://localhost:8080/hello` 요청 시 'HelloWorld' 문구 출력하기
- [X] `http://localhost:8080/users?name={name}` 요청 시 'Hello {user} {n}번째 방문입니다.' 출력하기
- [ ] `http://localhost:8080/beans` 요청 시 직접 등록한 Bean들의 이름 반환하기

### 💪 개인 목표

- 철저히 문서화 하기
- 도메인형 패키지 구조 익숙해지기
- Angular 커밋 컨벤션 익숙해지