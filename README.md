### 객체지향 생각해보기

1. 현재 코드에서는 두 테스트의 결과가 다르게 나오지 않는다.
    1. Provider가 Calender 객체를 내부에서 생성하고 인스턴스의 정보를 가져와 사용한다.
    2. 테스트를 위해서는 개발자가 임의적으로 given 값을 조정할 수 있어야한다.
    3. 위 코드에서는 provider가 아무런 input없이 여러 책임을 가져서 테스트가 힘들다.
2. Provider가 Calendar인스턴스의 선언까지 담당하고 있다.
    1. Calendar 의존성을 주입받는 방식으로 수정해야할 것 같다.
    2. DateMessageProvider는 말그대로 시간 정보를 받아 출력하는 것만 담당하게 바꾼다.

### 테스트 코드작성하기

- 기본적으로 작성된 테스트 코드를 작성하기위해 의존성 주입을 해본다.
- ApplicationContext에 대한 실습을 진행해보면서 감을 익힌다.

- 열심히 작성했다. 컨텍스트인터페이스 구현체들 살펴보면서 적절한 것 찾아보기.

## ApplicationContext

- 인터페이스임. 사용목적, 설정 파일 형식에 맞게 구현 클래스 골라서 사용
- `BeanFactory` 인터페이스를 상속함. 다른 인터페이스들도 많이 상속함.
- **스프링 빈 컨테이너 기능이 `BeanFactory` 인터페이스에 정의되어 있음.**

- **환경 변수를 추상화한 Environment 객체** 제공
- **스프링 빈 리스트**를 리턴하는 여러 메소드 제공
- BeanFactory들은 부모-자식간 관계를 맺을 수 있음. 부모 BeanFactory를 리턴받을 수 있는 메소드 제공
- **국제화 메시지**를 처리할 수 있는 메소드 제공
    - 지역 정보에 맞는 적절한 언어를 제공. → `MessageSource` interface → `Locale` 객체 기반
- 스프링  프레임워크에서 사용할 수 있는 **이벤트**들을 생성할 수 있는 메소드를 제공
    - 애플리케이션 내부에서 이벤트를 게시, 구독.
    - 내부 비즈니스 로직을 느슨하게 분리.
    - 만들기 어려움. → 스레드를 분리해서 돌리면 언제 실행될지 모름.
- 패턴을 이용하여 **Resource**를 다룰 수 있는 메소드 제공

---

## 스프링 빈 스코프

- 스프링 빈 객체를 생성하는 시간부터 객체가 소멸되기까지 그 기간을 **스프링 빈 스코프**라고함.
- 스코프에따라 스프링 빈 객체가 다를 수 있음. (Object.equals로 비교하면.)
- 6가지 스코프 설정이 있음
    - **singleton:** default, 스프링 빈 컨테이너에서 오직 단 한 개만 생성.
        - but 런타임에는 여러개 있을 수 있음. (스프링 빈 컨테이너에만 1개 유일하게 있는거)
    - **prototype:** 스프링 빈 컨테이너에서 여러 객체 생성. DI마다 새로운 객체를 생성해서 주입.
    - **request:** 웹 기능 한정. HTTP 요청을 처리할 때마다 새로운 객체를 생성.
    - **session:** 웹 기능 한정. HTTP Session과 대응되는 새로운 객체를 생성.
    - **application:** 웹 기능 한정. Servlet 컨텍스트와 대응하는 새로운 객체 생성.
    - **Websocket:** 웹 기능 한정. Web Socket Session과 대응하는 새로운 객체 생성

- 싱글턴이 의미하는바?
    - JVM에서 유일한 객체를 만드는 것을 의미.
    - **스프링의 singleton 스코프는 의미가 다르다.**
        - 스프링 빈 컨테이너 안에서만 유일한 객체임. JVM내에서는 여러 개의 객체가 있을 수 있음.

### **멀티스레드 환경에서 thread-unsafe객체를 싱글톤으로 등록하면?**

```java
@Bean
public DateFormatter singletonDateFormatter() {
	return new DateFormatter("yyyy -....");
}


public class DateFormatter implements Formatter<Date> {
	
	//thread-unsafe
	private SimpleDataFormat sdf;

	...

	// sdf 자체가 unsafe임.
	public Date parse(String dateString) {
		return sdf.parse(dateString);
	}

```

- 여러 곳에서 의존성 주입이 되어도 동일한 하나의 스프링 빈이된다.
- 스레드로 100회 singleonDateFormatter를 부름.
    - DateFormatter안에서 parse()가 멀티스레드에 안전하지않음
- 스레드에서 실행할때마다 컨테이너에서 객체를 가져와서 실행함.
- 예외가 생김. 스프링 빈 객체는 하나인데 결과값 자체가 에러임.

### **그럼 이런 경우에 어캐해결해?**

- 물론 스레드에 안전한 클래스나 메소드를 쓰는게 좋다.
- 아니면 parse()메서드 내부에서 new를 계속해주는거임.
    - 사용할때마다 객체를 새로생성해서 사용함. → 멀티 스레드에 safe함.
- DateFormatter의 스코프를 prototype으로 변경. → getBean할 때마다 새로운 객체를 만들어줌.