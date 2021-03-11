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

