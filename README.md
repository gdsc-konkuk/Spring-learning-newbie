<aside>
➡️ 스프링이 프로젝트 시작에 어떻게 스프링 빈컨테이너가 작동하는지 적어보자.

</aside>

- 스프링 빈 컨테이너 구현체에 따라 정해진 포맷의 설정파일을 로딩한다.
    - 자바클래스, XML, 그루비… 우리는 지금은 자바클래스로 하는 것같다.
- 설정 파일에 정의된 스프링 빈 정의를 로딩하고, 지정된 클래스 패스에 위치한 클래스들 스캔, 스프링 빈 정의 있으면 로딩.
- 이제 컨테이너에서 빈쭈루룩생성. 그리고 애들끼리 의존성있으면 서로서로 조립함.

<aside>
➡️ 기억을 더듬어… 그러면 스프링 빈을 어떻게 정의하지?

</aside>

- 자바 설정 클래스에서 어노테이션 사용하기 → `@Bean` 너무 자주 본다우리
- 스테레오 타입 애너테이션 사용 → 이건 뭔지 모름.
- BeanDefinition 인터페이스를 구현하여 정의 → 이거 스프링 2주차 강의에서 했다우리.
    - RootBeanDefinition을 new해서 만들고 컨테이너에 메타정보지정해서 등록하기 했음.

```java
// 비어있는 Shine정보를 담은 오브젝트 생성
        BeanDefinition shineDef = new RootBeanDefinition(Shine.class);
        // 빈 프로퍼티 설정
        shineDef.getPropertyValues().addPropertyValue("name", "Gdsc Konkuk");

        // TODO : 빈 메타정보를 hello2 라는 이름으로 컨테이너에 등록
        context.registerBeanDefinition("hello2", shineDef);
```

- xml 설정…

### 1. 자바-설정파일로 의존성 주입

```java
// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기
@Configuration
public class AuthenticationPrincipalConfig {

    // TODO: AuthService 빈을 등록하기
    @Bean
    public AuthService authService() {

        return new AuthService();
    }

    // TODO: AuthenticationPrincipalArgumentResolver를 빈 등록하고 authService에 대한 의존성을 주입하기
    @Bean
    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {

        return new AuthenticationPrincipalArgumentResolver(authService());
    }
}
```

1. 우선 지금 보는 자바파일이 설정파일인거를 명시해야하니까 `@Configuration` 을 달아준다.
2. AuthService를 빈으로 등록하려고 `@Bean` 어노테이션을 달아줬다.
    1. 그리고 반환하는 거는 새로운 AuthService 객체를 반환한다.
    2. 음.. 자바 설정파일로 의존성을 주입하니까 AuthService에 `@Bean` 이나 `@Service` 를 다는게 아니라 그냥 객체를 받아야한다고 생각했다.
3. Authentication…Resolver를 빈등록하고 의존성을 주입한다.
4. 빈으로 등록하고 생성자에 AuthService 객체를 넣어줬다.
    1. 이게 맞는지 잘모르겠다.
    2. authService도 빈으로 등록해놓았기 때문에 스프링 빈사이에 참조가 일어난다고 생각했다.

결과 : 테스트 통과!

<aside>
➡️ Plus Alpha

</aside>

- 자바 설정파일을 사용하는 경우 하나의 애플리케이션에 여러개의 설정 클래스가 있을 수 있다.
    - 음 config가 webmvc도 있고 security도 있고 … 많을 것 같긴하다.
- ***이때, 다른 자바 설정 클래스에 정의된 스프링 빈은 메서드 참조방식으로 의존성 주입을 할 수 없다.***
    - 지금 위에 코드보면 authService() 이거로 주입을 해주는데 이게 막히는 것 같다.
- 생성자를 사용해서 의존성 주입을 해주면 된다고한다.
    - autowired로 필드 주입도 가능한것 같음.

## Environment Test

### EnvironmentTest - key test

- **PropertySourceConfig 설정을 통해 ApplicationContext를 생성하고 JwtTokenKeyProvider를 조회하여 secret key 주입을 검증하는 테스트**
- **PropertySourceConfig 클래스에서 테스트를 성공시키기 위한 설정을 해야함**

- 저 두개를 어떻게 해야할까… 우선 테스트를 돌려보자
- 오호.. 빈으로 등록이 안됨. 우선 스프링 빈 등록 확인.
- 아래도 빈이 등록 안됨… 스프링 빈 등록부터 하자.
- 근데 뭐를 등록하지
    - PropertySourceConfig.class로 어플리케이션 컨텍스트 객체를 만들었다.
    - 리스트를 확인하니 여러 프로세서등.. ValueConfig만 등록되어 있다.
- 우선 하라는데로 해보자

### 1. PropertySourceConfig 설정하기


1. 우선 이것도 자바 설정파일이라서 `Configuration` 붙이기
2. application.properties를 자동으로 연결시켜주나?
    1. 오토와이어를 달았는데 없어도 잘된다.
    - PropertySource를 달아줘서 알아서 연결시켜주는건가?
    -

   [Properties with Spring and Spring Boot | Baeldung](https://www.baeldung.com/properties-with-spring)

- 역시 책만한게 없다. “스프링부트로 만드는 msa컴포넌트”

- 스프링 부트 프레임워크는 애플리케이션 코드와 애플리케이션을 설정할 수 있는 데이터를 분리할 수 있다.
- 자바에서는 이런 데이터를 .properties 확장자를 갖는 파일에 키/값 형태로 저장및 관리.
    - 스프링부트 프레임워크는 [application.properties](http://application.properties) 외에도 yaml 포맷의 파일도 제공한다.
    - 그러니까 application.properties나 application.yml을 찾아 로딩하는 거다!
- 스프링 프레임워크는 순서에 따라 정해진 경로에서 두 파일을 찾는데
    1. classpath 루트 패스
    2. classpath에 /config 경로
    3. 패키징된 애플리케이션이 위치한 현재 디렉토리
    4. 패키징된 애플리케이션이 위치한 현재 디렉토리의 /config
    5. /config 의 하위 디렉토리
- 음.. 잘 모르겠는데 그렇다고 한다.
    - src/main/resources에 보통 넣는데 이게 classpath 최상위 경로에 있어서 바로 가져온다고함.
- 그리고 뭐 배포과정에서 jar파일에 포함되는 거 가지고 환경분리를 어캐 하는지 적혀있는데…
    - 첫번째는 프로파일을 사용한다고함. 진짜 모르겠어서 우선 넘어가자.
    - 두번째는 jar파일 외부에 위치하는 application.properties를 사용하는방법.

### 근데 결국 지금 해야하는거? 어떻게 가져와서 읽어

- 파일에 정의된 데이터를 스프링 빈에 주입하려면 `@Value` 애너테이션을 사용한다.
    - 클래스, 메서드, 파라미터에 데이터 주입가능하다,
    - 메서드에 할때는 setter패턴으로 정의되어야 한다.

```java
@Value("${springtour.domain.name:springtour.io}"}
private String springtourDomain
```

- 이거로 살펴보자
    - springtour.domain.name키를 정의하는데 정의가 안되어있으면 “springtour.io”를 사용한다.
    - ${}은 프로퍼티, #{}은 스프링 빈을 의미한다!! → SpEL표현식을 찾아보자..

- 근데 우리는 Environment 객체를 사용해야하는 것 같음.
    - 스프링 프레임워크가 애플리케이션이 실행되면 Environment객체를 스프링 빈으로 생성한다고함.
    - 우리가 따로 해줄필요없이 그냥 가져다 쓰면 됨?
    - 해보니 안된다.

- 여러가지 뒤져보다가 @PropertySource에 대해서 알게되었다 ㅎㅎ
    - Environment객체의 getPropertySource메소드로 설정 파일에 접근한다고한다.
    - 여러 예제들을 보니 ResourcePropertySource 객체에 classpath를 인자로 줘서 직접 가져오는 것 같은데 한줄로 컷 가능한 어노테이션이 있다.
- 그렇다고 안 볼수는 없음.

```java
ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
ConfigurableEnvironment env = ctx.getEnvironment();                      
MutablePropertySources propertySources = env.getPropertySources();

try 
{
    propertySources.addLast(new ResourcePropertySource("classpath:admin.properties"));
            
    System.out.println( env.getProperty("admin.id") );
    System.out.println( env.getProperty("admin.pw") );
} catch (IOException e) {}
```

- 인터넷을 뒤지다가 이런 예제를 찾았다. 애플리케이션 컨텍스트에서 Environment를 끌어와서 사용하는데
- PropertySources에 우리가 사용할 source를 추가하고 가져와서 쓰는것같다.

> In certain situations, it may not be possible or practical to tightly control property source ordering when using @PropertySource annotations. For example, if the @Configuration classes above were registered via component-scanning, the ordering is difficult to predict. In such cases — and if overriding is important — it is recommended that the user fall back to using the programmatic PropertySource API. See ConfigurableEnvironment and MutablePropertySources javadocs for details.
>

- 실제로 @PropertySource 어노테이션의 자바 독스에도 어노테이션 만으로 컨트롤하기 어려운 경우가 있을 수 있어..예제처럼 직접 하는것을 추천한다고 한다.

## ConfigurationProperties?

@ConfigurationProperties
@PropertySource("classpath:application-test.properties")
public class SecurityJwtTokenProperties {
private String secretKey;
private String expireLength;

    public SecurityJwtTokenProperties(@Value("${security.jwt.token.secret-key}")String secretKey, @Value("${security.jwt.token.expire-length}")String expireLength) {
        this.secretKey = secretKey;
        this.expireLength = expireLength;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getExpireLength() {
        return expireLength;
    }
}

- 위에서 어노테이션은 내가달아준 것이구
- secretKey랑 expireLength는 application-test.properties에 명시되어있어서 `@Value`  를 써서 직접 넣어줬다.
- 테스트코드에 `@ConfigurationPropertiesScan` 이 있어서 이게 뭔가하고… 그냥 막 코드짜다가 `@Component` 만 달면 아예 빈을 못읽길래 머지했다.
- 인텔리제이에서 어노테이션 타고 들어가자 `@ConfigurationProperties` 가 달린게 아닌 클래스들은 무시한다고 하더라.. 그래서 달아줬는데 정확히 저게 뭐지?

- 한번 읽어보고 오호 이런게 있구나..
  yaml로 작성하면 prefix는 조금 편하게 쓸것같다.
- properties파일에서 읽어올때 앞에꺼 다 적었는데 이것도 생략이 되나?

<aside>
➡️ Official Docs는 configuration Properties를 POJO를 통해 분리하는 것을  권장한다. →  properties에 정보가 엄청 많을 수 있는데 그거를 다 자바 클래스로 만들고 등록하여 사용하는 것ㄴ가유?__ 

</aside>