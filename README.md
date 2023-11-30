# ✨ `WebConfig` 파일로 MVC 설정하기

SpringBoot를 사용해서 웹 애플리케이션을 짤 때, 필요에 따라 Spring MVC의 설정을 건드릴 수 있다.

## 🤔 `WebConfig` 파일 ?

`WebConfig`: Web 구성에 대해 지정하는 내용이 담겨있는 설정 파일  
SpringBoot → `WebMvcConfigurer` 인터페이스 구현을 통해 `WebConfig` 파일 작성  
설정과 관련된 파일을 분리해서 관리함으로써 → 협업 시 다른 개발자가 기존 프로젝트의 설정 파일만 보면 되기에 **유지보수** 원활

## 🔍 `WebMvcConfigurer` 인터페이스

- Spring MVC 구성을 사용자가 정의하는 데 사용되는 인터페이스
- Handler Mapping, Handler Adapter, Interceptor, View Resolver, Message Converter, Resource Handler, Argument Resolver,
  Return Value Handler, CORS 등등을 설정 가능

```java
public interface WebMvcConfigurer
```

> Defines callback methods to customize the Java-based configuration for Spring MVC enabled via `@EnableWebMvc`.
>
> `@EnableWebMvc`-annotated configuration classes may implement this interface to be called back and given a chance to
> customize the default configuration.

굉장히 많은 옵션들이 존재 → 그 중에서 내가 커스텀하고 싶은 옵션에 대해서만 `@Override`를 통해 **재정의**

미션 관련하여 공부한 3가지 옵션

- View Controllers
- Interceptors
- Argument Resolvers

## ⚙️ View Controllers 설정

### 🤔 View Controllers란 ?

Spring의 View Controllers = URL 매핑을 간단하게 처리할 수 있도록 도와주는 기능  
`Controller` 클래스를 작성하지 않고도 간단한 URL 매핑을 설정 가능 → 개발 시간 단축  
View Controllers 사용 → 요청 URL과 뷰 이름 매핑 + URL에 파라미터 전달 or 리다이렉트 가능

```java

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
}
```

위 코드에서 나오는 어노테이션

- `@Configuration`
- `@EnableWebMvc`

#### `@Configuration`

- `@Configuration` 어노테이션이 붙어있는 클래스 → Spring IoC 컨테이너가 초기화될 때 실행
- Spring IoC 컨테이너 = 애플리케이션 구동 시 가장 먼저 초기화 → `@Configuration` 어노테이션이 붙은 클래스를 찾아서 Bean으로 등록
- `@Configuration` 어노테이션이 붙은 클래스 = 애플리케이션 시작 시점에 실행 → 미리 필요한 Bean을 생성 + 설정 가능

### `@EnableWebMvc`

- Spring MVC를 사용할 때 필요한 설정을 자동으로 활성화하는 어노테이션
- ex) `@RequestMapping` 어노테이션을 사용하여 URL 매핑 처리, `@RequestBody` 어노테이션을 사용하여 Request Body를 처리하는 등의 설정 가능

