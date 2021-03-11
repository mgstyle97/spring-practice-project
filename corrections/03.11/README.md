# **<03.11>**



## **URL의 수정**

[REST](https://www.notion.so/REST-REST-api-RESTful-8ec73f3ad44d470182e4dc6981396352)에 대한 학습을 새로 하면서 기존에 프로젝트에서 지정했던 URI에 수정이 필요하다 생각했다.



**기존의 URI**

`POST /regist`로 지정하여 새로운 사용자의 등록을 매핑했다.



**하지만** REST API를 설계하는 기본 규칙 중 자원에 대한 행위는 HTTP Method로 표현하면서 동사 표현이 들어가면 안된다는 것에 대해 배우고 이를 수정하기 위해 매핑 URI를 다음과 같이 수정하게 되었다.

`POST /users`

이에 따른 코드의 수정도 필요했다.

```java
@RequestMapping("/users")
public class UserRegisterController {

    private UserRegisterService service;

    @Autowired
    public void setService(final UserRegisterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Response> register(
        @RequestBody @Valid UserRegisterCommand command) {
        service.register(command);
        URI uri = URI.create("/register");
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully register new user data"));
    }

}
```



## 코드 리펙토링

기존의 코드에서 Advice는 다음과 같았다.

```java
@ExceptionHandler(NoEqualsPassword2ConfirmPasswordException.class)
public ResponseEntity<Response> handleNoEqualsPw2ConfirmPwExp() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new Response("No match password with confirm password"));
}

@ExceptionHandler(HttpMessageNotReadableException.class)
public ResponseEntity<Response> handleInvalidJSONFormatExp() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new Response("Invalid JSON format"));
}
```

잘못된 요청에 의해 `HttpStatus.BAD_REQUEST`로 동일하게 HTTP 상태를 반환하지만 반환할 때 메시지만 다른 형태로 전달해왔다.



**이에 따른 중복된 코드가 너무 많아 이를 하나의 메서드로 묶어 메시지만 전달 받을 수 있도록 코드를 변경하였다.**

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Response> handleBindData(MethodArgumentNotValidException ex) {
    String errorCodes = ex.getBindingResult().getAllErrors()
        .stream()
        .map(error -> error.getCodes()[0])
        .collect(Collectors.joining("."));
    return getBadRequestResponseEntity("errorCodes = " + errorCodes);
}

private ResponseEntity<Response> getBadRequestResponseEntity(final String message) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new Response(message));
}
```

`getBadRequestResponseEntity()`는 BAD_REQUST로 동일한 상태를 반환하지만 메시지만 다른 처리를 위해서 정의해줬다.