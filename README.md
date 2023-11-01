# 과제 1 : 질문 답해보기

## @RestController 에 들어가보면 4개 어노테이션이 있는데 각각의 의미는?

### 1.`@Target`의 의미
- __답 : Annotation`이 annotate될 수 있는 컨텍스트를 명시함. `클래스`에만 붙는지 `메소드`에 붙는지 등.__


- `@ResponseBody`의 경우 `ElementType.TYPE`, `ElementType.METHOD`가 달려있음.
  - 따라서 `@RestController`에도 붙어있고, 직접 컨트롤러의 메소드에도 달아줄 수 있음.

### 2. `@Retention`의 의미
- __답 : `Annotation`이 컴파일 단계나 런타임등 어느 단계까지 유지되는 지를 결정.__


- `@Target`이랑 비슷하게 `meta-annotation`으로 어노테이션을 정의하기 위해 쓰이는 것 같음
- `컴파일러` 만 어노테이션을 확인할 수 있는지, 런타임상에서도 계속 어노테이션을 확인할 수 있는지 지정해줌.
- 우리는 런타임상에서도 컨트롤러를 계속 써야하니까 `@RestController`에는 `@Retention(RetentionPolicy.RUNTIME)`으로 작성됨.

### 3. `@Controller`의 의미
- __답 : 어노테이션이 달린 클래스를 애플리케이션의 컨트롤러로 등록하는 역할.__


- 서버에 요청이 들어오면 `Dispatcher` 라는 애가 `@Controller`을 달고 있는 클래스들을 찾고 그 안에서 `@RequestMapping`이 달린거를 찾는다.
  - 특정 URI로 들어온 요청을 처리할때 매치되는 컨트롤러로 넘겨주는 역할로 이해.
- `@Controller` 는 뷰를 반환하고 view-resolver에서 이를 처리하여 다시 넘겨준다.
  - `ResponseEntity`를 만들어서 반환하면 그냥 데이터를 뷰 처리안하고 넘겨준다
  - `@RestController` 은 이름처럼 기본형이 데이터만 넘겨준다.
- 궁금한점 : 특정 도메인을 담당하는 컨트롤러에서 뷰를 반환하는 경우랑 데이터만 반환하는 경우가 있으면 `@Controller`어노테이션을 사용해서 따로 처리를 해주나?
### 4. `@ResponseBody`의 의미
- 답: 어노테이션이 붙은 메소드의 반환값을 `HTTP ResponseBody` 로 변환시켜준다.
- 내부적으로는 HttpMessageConverter이 담당한다.
- 해당 어노테이션이 달려있으면 view-resolve 과정을 거치지 않고 데이터를 HTTP ResponseBody에 넣어 반환한다.
- `@RestController`에 `@ResponseBody`가 달려있어서 데이터를 반환하면 그냥 뷰처리 없이 넘어간다.
---
# 과제 2

## DAO 코드에서 문제점을 찾고 해결해보자.

```java
    @RestController
public class HelloController {

    private HelloDao helloDao;

    public HelloController(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    // 생략...
}

```
- __답 : 생성자를 통해 의존성을 부여해주고 있는데 DAO객체가 런타임상에서 여러개가 생성되면 안되니까 `final`을 붙여줘야한다.__

```java
    @RestController
public class HelloController {

    private final HelloDao helloDao;
    
    public HelloController(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

}
```
---
# 과제 3
## 기능 구현을 위해 DAO 코드를 작성해보자

1. 어노테이션에 들어갈 것
   - `@Repository`
2. 첫번째 메소드
```java
    public void insertUserName(String userName) {
        String sql = "insert into USERS (NAME) values (?)";
        jdbcTemplate.update(sql, userName);
    }
```
3. 두번째 메소드
```java
    public Optional<Integer> countByUserName(String userName) {
        String sql = "select COUNT(*) from USERS where NAME = (?)";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, Integer.TYPE, userName));
    }
```


# 과제 4

### 빈을 출력하는 컨트롤러만들기

- 애플리케이션 컨텍스트에서 `getBeanDefinitionNames` 메소드를 사용하니 사용되는 모든 빈들이 다 출력됬다.
- 방법이 없을까 하고 찾아보다가 `ListableBeanFactory` -> `BeanFactory` -> `FactoryBean` -> `BeanDefinition`에 도달했다.
- 여기서 딱 하나가 있었다. 바로 아래 부분이다. 
```java
    /*
    * Role hint indicating that a {@code BeanDefinition} is a major part
    * of the application. Typically corresponds to a user-defined bean.
    */
      int ROLE_APPLICATION = 0;

```
- 유저가 define한 빈이면 저게 있다는 것 같은데...

```java
	/**
	 * Get the role hint for this {@code BeanDefinition}. The role hint
	 * provides the frameworks as well as tools an indication of
	 * the role and importance of a particular {@code BeanDefinition}.
	 * @see #ROLE_APPLICATION
	 * @see #ROLE_SUPPORT
	 * @see #ROLE_INFRASTRUCTURE
	 */
	int getRole();
```
- 그런데 모든 빈의 Definition을 보고 getRole을 통해 필터를 걸면 해결되지 않을까.
- ... 아직 해결못했습니다. 