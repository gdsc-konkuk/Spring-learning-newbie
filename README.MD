# Configuration 설정

---

## xml 기반 설정

---

- xml 파일
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <!-- UserService 빈 등록 및 의존성 주입 -->
    <bean id="userService" class="com.example.UserService">
        <property name="userRepository" ref="userRepository"/>
    </bean>
    
    <!-- UserRepository 빈 등록 -->
    <bean id="userRepository" class="com.example.UserRepository"/>
    
</beans>
```

- xml 파일 기반 스프링 컨테이너 생성
```java
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("xml의 경로");
    }
}
```

## java 기반 설정

---

-  Java Config 클래스
```java
@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }
}
```

- Java Config 클래스 기반 스프링 컨테이너 생성
```java
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
```

## environment 기반 설정

---

- Environment 기반 Java Config 클래스
```java
@Configuration
@PropertySource("classpath:application.properties")
public class EnvironmentConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }
}
```

- application.properties
```properties
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/mydatabase
db.username=myuser
db.password=mypassword
```

- Environment 기반 스프링 컨테이너 생성
```java
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(EnvironmentConfig.class);
    }
}
```

## profile

---

### profile
- 스프링 어플리케이션 환경 구성에 사용되는 여러 설정의 집합(.properties, .yml와 같은 파일 형식이 해당)
- 프로파일은 기본적으로 main.resource 경로의 application으로 설정되어 있음
- application.property과 application-test.property에서 test가 profile를 구분하는 이름에 해당

### @Profile("profile 이름")
- 특정 빈이나 설정 클래스가 특정 프로파일에서만 활성화되도록 지정

### @ActiveProfiles("profile 이름")
- 테스트 클래스나 테스트 메서드에서 특정 프로파일을 활성화하기 위해 사용

## @ConfigurationProperties와 @Value의 차이
### @ConfigurationProperties
- 여러 프로퍼티 값을 한 번에 묶어서 빈에 주입하는 데 사용
- 특정 프로퍼티 그룹을 자바 빈에 바인딩
```java
@Component
public class MyComponent {
    @Value("${my.property}")
    private String myProperty;
}
```

### @Value
- 하나의 프로퍼티 값을 주입하는 데 주로 사용
- 주로 메서드나 필드 레벨에서 사용
```java
@Component
@ConfigurationProperties(prefix = "my")
public class MyProperties {
    private String property1;
    private int property2;
}
```
- yml 파일
```yml
my:
  property1: "value1"
  property2: 42

```

## @PropertySource
- 직접 Property를 설정할 수 있음
- intelij community edition에서 자동으로 application 파일을 인식 못할 경우 도움이 됨
```java
@PropertySource("classpath:application.properties")
```
