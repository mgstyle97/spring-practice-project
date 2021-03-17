# <03.17>



## **코드 중복 제거**



각 도메인간 공통적으로 발생하는 오류에 대해 확인해보았다.

- `TypeMismatchException.class`

  `@PathVariable` 애노테이션을 통해서 url에 id를 통해 도메인에 대한 정보를 요청할 시 파라미터 타입과 일치하지 않는 경우 발생하는 오류



- `HttpMessageNotReadableException.class`

  테스트를 하기 위해 json에 데이터를 전송할 때 올바르지 않는 json 형식으로 요청을 할때 발생하는 오류



- `MethodArgumentNotValidException.class`

  `@Valid` 애노테이션을 사용하여 커맨드 객체에 대한 유효 검사를 실시할 때 해당 커맨드 객체에 대한 문제가 있을 때 발생하는 오류



> **이러한 공통된 오류들에 대한 처리를 `@ExceptionHandler` 애노테이션을 통해서 처리하게 되면 공통된 코드가 중복되게 처리되기 때문에 이를 동일하게 처리해주기로 했다.** 





**공통된 예외에 대한 처리**

`GlobalExceptionAdvice.java`

```java
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<Response> handleTypeMismatch() {
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(new Response("Type Mismatch"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleInvalidJSONFormatExp() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Invalid JSON Format"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleBindData(MethodArgumentNotValidException ex) {
        String errorCodes = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getCodes()[0])
                .collect(Collectors.joining("."));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("errorCodes : " + errorCodes));
    }

}
```



이를 처리하는 도중 `@RestControllerAdvice`를 지정한 클래스 역시 빈으로 등록해주지 않으면 이러한 오류 처리를 해주지 않는다는 것을 알았다.

그래서 `GlobalExceptionAdvice.java`를 빈으로 등록해주어 도메인 별로 지정해준 것이 아닌 공통된 에러에 대해 처리를 해주도록 하였다.