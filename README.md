## 과제1 : `@RestContoller` 살펴보기

### what is `@Target`?

`ElementType` enum을 통해 annotation의 **적용 대상**을 명시한다.

`@RestController`에서는 `@Target(ElementType.TYPE)`으로 class와 interface 선언에 사용된다고 명시하고 있다.

### what is `@Retention`?

`RetentionPolicy` enum을 통해 annotation의 **적용 범위**를 명시한다.

`@RestController`에서는 `@Retention(RetentionPolicy.RUNTIME)`으로 annotation이 runtime까지 유효하다고 명시하고 있다.

| `RetentionPolicy가` `SOURCE`라면 compile-time-only이고, `CLASS`일 경우 bytecode에는 포함되지만 runtime에 유효하지는 않다.

### what is `@Controller`?

Spring MVC의 `Controller`임을 명시한다.

**3 layered architecture** 중 **Presentation Layer** 에 해당한다.

### what is `@ResponseBody`?

`View`가 아닌 `Data`를 반환하는 `Controller`임을 명시한다.

Sping MVC는 기본적으로 `View`를 Resolve하도록 설계되었지만, 현대의 RESTful API는 `Data`를 반환한다.

## 과제2 : `final` 살펴보기

```java
private final HelloDao helloDao;

@Autowired
public Users(HelloDao helloDao){
        this.helloDao=helloDao;
        }
```

### What is `final`?

java에서 불변성을 보장하기 위해 사용하는 keyword로, `final`로 설정한 요소는 값을 수정할 수 없다.
**변수**는 단순 `value` 변경이, **method**는 `override`가, **class**는 `상속`이 불가능해진다.

### `DAO`는 불변해야 하는가?

`Controller`(보통은 `Service`이긴 하겠으나...)에서 `DAO`를 변경할 필요가 있을까? 직관적으로 생각했을 때 떠오르는 일이 없을 것이다.
`DAO`는 `DataSource`(**DB**, **remote source** etc)에서 직접 data를 받아오거나 수정하는 기능을 담당한다.
즉, `DataSource`가 바뀌지 않는 이상 `DAO`가 변경되선 안 된다.

프로그램/서비스를 운영하면서 특정 이유로 **타 DB로 migration**하거나, **외부 API를 옮기**거나 할 경우에 `DAO`를 변경하게 되겠지만,
이 작업은 반드시 **Program Level의 "재실행"이 필요**하다.
즉, **run-time**에 `DAO`의 변경은 없으며,
이를 `final` keyword로 명시하는것은 **기능 안정성** 측면 뿐만 아니라 **가독성** 향상에도 도움이 된다.