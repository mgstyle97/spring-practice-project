# <03.29>



## 자동 생성 메서드의 이름으로 인한 오류



login 기능 시 쿠키와 세션을 다루기 위해 다음과 같이 command와 controller를 정의했다.

`LoginCommand`

```java
public class LoginCommand {

    @NotBlank(message = "ID를 입력해주세요.")
    private String id;
    @NotBlank(message = "PASSWORD를 입력해주세요.")
    private String password;
    private boolean isRememberId;

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setRememberId(final boolean isRememberId) {
        this.isRememberId = isRememberId;
    }

    public boolean isRememberId() {
        return this.isRememberId;
    }

}
```



`LoginController`

```java
...
@PostMapping
public ResponseEntity<Response> login(@RequestBody @Valid LoginCommand command,
                                          HttpSession session,
                                          HttpServletResponse response) {
    User userInfo = this.authService.authenticate(command);
    session.setAttribute("user", userInfo);

    Cookie userIdCookie = new Cookie("ID", command.getId());
    userIdCookie.setPath("/");  // 모든 경로에서 접근 가능
    if(command.isRememberId()) {
        userIdCookie.setMaxAge(60 * 60 * 24);
    } else {
        userIdCookie.setMaxAge(0);
    }
    response.addCookie(userIdCookie);

    return ResponseEntity.ok(new Response("Successfully login"));
}
```



위와 같이 json으로 전달받은 command 객체의 isRemeberId를 통해 쿠키의 생성 여부를 결정한다.



**하지만**

IntelliJ IDE에서 자동으로 생성된 Getter/Setter 메서드 중 setRememberId()로 인해 이를 spring에서 자동으로 실행시켜주지 않아 항상 쿠키가 생성되지 않도록 로직이 수행된다.

그 이유는 boolean 타입인 isRememberId 변수의 이름대로 메서드 이름이 지어지지 않았기 때문이다.

그렇기 때문에 Setter 메서드인 setRememberId를 setIsRememberId로 변경해줘야 했다.

```java
public void setIsRememberId(final boolean isRememberId) {
    this.isRememberId = isRememberId;
}

public boolean isRememberId() {
    return this.isRememberId;
}
```

