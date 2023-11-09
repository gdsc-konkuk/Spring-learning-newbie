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

