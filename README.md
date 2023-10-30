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
