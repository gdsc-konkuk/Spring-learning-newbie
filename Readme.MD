# 1. Target의 의미는?

---

- 해당 어노테이션을 적용할 대상의 타입을 지정(사용 범위를 제한). 사용 범위를 명시적으로 제한함으로써 코드 사용의 안정성과 가독성, 유지보수에 이점이 있음.

# 2. Retention의 의미는? 

---

- 어노테이션의 수명 주기 
- RetentionPolicy.SOURCE: 컴파일 시에만 유효, 컴파일된 클래스 파일에는 포함되지 않음. 런타임에는 어노테이션이 존재하지 않음. 
- RetentionPolicy.CLASS: 컴파일된 클래스 파일에 포함, 런타임 시에는 사용할 수 없음. 어노테이션 정보를 유지하되 런타임에는 액세스할 수 없도록 함.
- RetentionPolicy.RUNTIME: 컴파일된 클래스 파일에 포함, 런타임 시에도 어노테이션 정보에 접근 가능. 리플렉션(조만간 공부 후 포스팅 예정)을 통해 어노테이션 정보를 검색, 처리 가능. 

# 3. Controller의 의미는?

---

- spring MVC 컨트롤러 사용을 표시

|      |@Controller|@RestController|
|------|-|-|
| 용도   |웹 애플리케이션 컨트롤러 역할,<br>HTML 페이지 렌더링|RESTful 웹 서비스 엔드포인트,<br>JSON 또는 XML 데이터 반환|
| 반환 값 |뷰 페이지(HTML),<br>모델 데이터|데이터 객체,<br>JSON,<br> XML|

- View를 반환하는 Controller 구조 - [출처](https://mangkyu.tistory.com/49)
  ![View를 반환하는 Controller 구조, 출처: https://mangkyu.tistory.com/49](https://velog.velcdn.com/images/dev98/post/803011b4-134b-4a00-95b1-bd0e7398c61e/image.png)

- Data를 반환하는 Controller 구조 - [출처](https://mangkyu.tistory.com/49)
  ![Data를 반환하는 Controller 구조, 출처: https://mangkyu.tistory.com/49](https://velog.velcdn.com/images/dev98/post/84bf8b84-72a4-455a-b776-01b57d9562bc/image.png)

- RestController 구조 - [출처](https://mangkyu.tistory.com/49)
  ![RestController 구조, 출처: https://mangkyu.tistory.com/49](https://velog.velcdn.com/images/dev98/post/fcf53cd2-390b-47a6-abea-43d52febbedf/image.png)

# 4. ResponseBody의 의미는?

---


- 메서드의 반환값을 직렬화, HTTP 응답 본문으로 설정

# 5. 생성자 주입 문제

---

- HelloDao는 런타임 중 필드나 메서드가 변경될 일이 없기 때문에 싱글톤 빈 생성 및 관리 차원에서 'final' 키워드를 붙여주는 것이 안전함.
```java
@RestController
public class UserController {
    private final HelloDao helloDao;

    public UserController(HelloDao helloDao) {
        this.helloDao = helloDao;
    }
}
```