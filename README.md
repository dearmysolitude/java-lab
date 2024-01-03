# Java 미니프로젝트: 회원관리 프로그램

- Lecture: [즐거운 자바 63: 회원관리 프로그램](https://www.youtube.com/watch?v=HEsAMjd8zpo)
- UML: https://draw.io
- .gitignore: https://www.toptal.com/developers/gitignore/

## 회원 관리 프로그램 만들기

1. 회원 등록
2. 회원 목록 보기
3. 종료

- 프로그램을 실행하면 /tmp/users.dat 파일에서 회원정보를 읽어온다.
- 해당 파일이 없을 경우 읽어오지 않는다.
- 프로그램 종료시, 메모리에 있는 회원 정보를 /tmp/users.dat에 저장한다.
- 프로그램을 강제 종료하면 입력된 회원 정보는 저장이 안될 수도 있다.

## 구현

### 회원 등록

```dtd
회원의 email을 입력하세요.
...
회원의 이름을 입력하세요.
...
회원의 생년을 입력하세요.
...
등록되었습니다.
```

### 회원 목록 보기
```dtd
email 이름 생년
---------------------
uremail@gmail.com 홍길동 1980
...
...
```

## 구성

[UML](document)

### UserUI

- menu()
  - 메인 메뉴 출력, 이동
  - int 입력
  - 메뉴 이동
- regUser()
  - 유저 정보 입력, 등록
  - 이메일/이름/생년 입력
  - 유저 정보 리턴
- listUser()
  - 화면에 회원 정보 출력: List<User>

### User

- email
- name
- birthYear

### UserDAO(Data Access Obejct)

- 회원 정보 저장을 위한 클래스
- 생성자: UserDAO(파일명)
- saveUsers(List<User>)
  - 유저 정보를 저장함
- loadUsers()
  - 유저 정보 읽어옴
  - List<User>

### UserMain

- 메인 메서드를 가진 진입점
- UserUI와 UserDAO를 사용