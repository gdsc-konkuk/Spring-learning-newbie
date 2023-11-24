## 어려웠던 부분

### reflection

요약하자면, 동적으로 object의 정보를 조회하는 기능입니다.

> "`ObjectMapper`는 reflection으로 구현되었기 때문에, setter 없이도 동작한다."

라는 글을 읽어서 reflection이 무엇인지 알아야겠다고 생각했습니다.
setter 없이 외부에서 property의 수정이 가능하다면 안심하고 code를 작성할 수 없습니다.

(java와 같은 major 언어에서 그러한 API가 있을 리 없다고 생각했습니다.)

결론적으로 역시 외부에서는 setter 없이 내부 property를 수정할 수 없습니다.

reflection을 통한 data/object의 변조는 객체를 **변경**하는 것이 아닌,
새로운 객체를 **반환**하는 것입니다.

(js 진영의 `splice` vs `toSpliced`가 생각나네요)

### `@ModelAttribute`

request params(query string, path parameter)를 통해서
특정 instance(model)를 생성합니다.

reflection을 통해서 필요한 properties를 확인하고,
우선 생성자를 통해 주입, 이후 남은 property는 setter로 주입합니다.

`@RequestParam`이 request params에 대한 **look up**이라면,
`@ModelAttribute`는 request prams으로부터의 **instantiation**입니다.

## 좋았던 부분

- **Error Handling** in Spring MVC : `@ControllerAdvice` & `@ExceptionHandler`
- **Content Negotiation** in Spring MVC : `consumes` & `produces`
- `location` header : `3xx`뿐만 아니라 `201`에도 필요하구나!
