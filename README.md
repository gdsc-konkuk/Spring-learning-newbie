### 1. 미션 진행 (by TDD)

![TDD_그림](https://lake-peach-ffa.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F9f7972e8-6377-4bf1-afce-c0cc09f81101%2Ff2aad59f-09d7-42fc-ad1e-ee0c76024a18%2FUntitled.png?table=block&id=1478782b-f274-4a07-b2e4-1569aaa512ee&spaceId=9f7972e8-6377-4bf1-afce-c0cc09f81101&width=2000&userId=&cache=v2)

#### 1-1. 우선 테스트부터 실행(실패 확인 단계)

TDD의 첫 단계 = 실패하는 테스트를 확인하는 것  
잊지말자

![테스트_코드](https://lake-peach-ffa.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F9f7972e8-6377-4bf1-afce-c0cc09f81101%2F331338c2-fbe6-450d-b87a-cf7ba8f75807%2FUntitled.png?table=block&id=d3585a75-fabc-4615-a8a6-d35dde60cd0f&spaceId=9f7972e8-6377-4bf1-afce-c0cc09f81101&width=2000&userId=&cache=v2)

다음과 같이 실패하는 것 확인하기

![테스트_실패_확인](https://lake-peach-ffa.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F9f7972e8-6377-4bf1-afce-c0cc09f81101%2Faf840d29-ef96-4db6-8fbb-5ecad52d226e%2FUntitled.png?table=block&id=e5b2a413-b47d-4129-bf32-e93c6109951f&spaceId=9f7972e8-6377-4bf1-afce-c0cc09f81101&width=2000&userId=&cache=v2)

#### 1-2) 터미널 로그 확인

테스트 수행 결과를 터미널에서 확인 후 → 로그를 바탕으로 다음 작업 진행

![터미널_로그](https://lake-peach-ffa.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F9f7972e8-6377-4bf1-afce-c0cc09f81101%2Fa82e04d5-9e65-4aaa-a2e5-5cab5920819a%2FUntitled.png?table=block&id=eedf708f-c1da-4502-805d-94236979be87&spaceId=9f7972e8-6377-4bf1-afce-c0cc09f81101&width=960&userId=&cache=v2)

#### 1-3) 레퍼런스 확인

- 학습이 필요하면 메서드 주석에 안내된 링크를 통해 레퍼런스 문서 확인
- 링크가 없거나 키워드가 있는 경우 → 구글링을 통한 학습

![레퍼런스_확인](https://lake-peach-ffa.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F9f7972e8-6377-4bf1-afce-c0cc09f81101%2F69d19103-efed-4a4b-a372-109f791ee1e8%2FUntitled.png?table=block&id=2400d45c-2754-4c36-9137-3c80609d546d&spaceId=9f7972e8-6377-4bf1-afce-c0cc09f81101&width=2000&userId=&cache=v2)

#### 1-4) 코드 구현

- 문서에서 안내한 방법을 코드로 직접 구현
- 이해가 되지 않아도 우선 수행시켜 테스트 성공시켜보기
- 한번에 이해하기 쉽지 않기 때문에 → 우선 동작하는 코드를 구현한 뒤 원리를 이해하는 순서로 학습하자

![코드_구현](https://lake-peach-ffa.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F9f7972e8-6377-4bf1-afce-c0cc09f81101%2F1507a461-ffb6-4068-bd61-3d33efea84de%2FUntitled.png?table=block&id=c0f08a01-7c9f-479f-9880-8e589fa18cf6&spaceId=9f7972e8-6377-4bf1-afce-c0cc09f81101&width=2000&userId=&cache=v2)

---

### `@GetMapping("/patterns/*")`와 `@GetMapping("/patterns/?")`의 차이
- `@GetMapping("/patterns/*")`:
  - 이 패턴은 `/patterns/`뒤에 **어떤 문자열**이 와도 해당 메서드와 매치
  - ex. `/patterns/a`, `/patterns/b`, `/patterns/anything` 등의 URL과 매치
  - `*` = 세그먼트 내의 모든 문자를 포함
- `@GetMapping("/patterns/?")`:
  - 이 패턴은 `/patterns/` 뒤의 정확히 **한 문자**만을 허용
  - ex. `/patterns/a`, `/patterns/b`와는 매치되지만, `/patterns/ab`, `/patterns/`, `/patterns/anything` 등은 매치 X
  - `?` = 한 개의 문자만을 대체

### `@RequestParam`과 `@ModelAttribute`의 차이
- `@RequestParam`:
  - 주로 단일 파라미터의 값을 HTTP 요청의 쿼리 매개변수(query parameters) or 폼 데이터(form data)에서 추출할 때 사용
  - 해당 어노테이션은 URL에서 `?name=value` 형식의 쿼리 스트링으로 전달되는 개별 값에 액세스하기 위해 사용
  - 간단한 타입 변환 가능 + 필수 여부도 지정 가능
- `@ModelAttribute`:
  - HTTP 요청의 파라미터를 객체의 필드와 매핑하여 객체를 새로 생성할 때 사용
  - 폼 데이터(form data)를 객체에 바인딩 → 이 객체를 모델에 추가하여 뷰(view)로 전달하는 데 주로 사용