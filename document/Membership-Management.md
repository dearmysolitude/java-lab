# 회원관리 프로그램

- **분류: 개념 적용 / 미니 프로젝트 / Java**
- Lecture: [즐거운 자바 63: 회원관리 프로그램](https://www.youtube.com/watch?v=HEsAMjd8zpo)
- UML: https://draw.io
- .gitignore: https://www.toptal.com/developers/gitignore/

## 특징

- 객체 직렬화를 통해 인스턴스 읽고 쓰는 작업을 용이하게 하였음
- 인터페이스 리팩터링을 통해 코드의 재사용성을 높임

## 기능 요구사항

1. 회원 등록
2. 회원 목록 보기
3. 회원 정보 수정
4. 회원 정보 삭제
5. 종료

- 프로그램을 실행하면 /tmp/users.dat 파일에서 회원정보를 읽어온다.
- 해당 파일이 없을 경우 읽어오지 않는다.
- 프로그램 종료시, 메모리에 있는 회원 정보를 /tmp/users.dat에 저장한다.
- 프로그램을 강제 종료하면 입력된 회원 정보는 저장이 안될 수도 있다.

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

## 클래스 구성

[UML]()

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
- editUser()
  - 유저의 정보를 출력한 후, 수정할 userId를 입력받음
  - 입력 받고 수정할 유저 정보가 없을 경우 메세지 출력: '수정할 유저의 정보가 없습니다.' 를 출력

### User

- email
- name
- birthYear

### UserDAO(Data Access Obejct)

- 객체 직렬화를 통해 인메모리로 데이터를 불러오고 저장하는 역할을 한다: List의 객체 직렬화(Serializable)
- 생성자: UserDAO(파일명)
- saveUsers(List<User>)
  - 유저 정보를 저장함, 프로그램 종료시 실행
- loadUsers()
  - 유저 정보 읽어옴, 프로그램 시작시 실행
  - List<User>

### UserMain

- 메인 메서드를 가진 진입점
- UserUI와 UserDAO를 사용

### 리팩터링: UserService / UserServiceInMemory

- 파일에서 읽어온 유저 리스트를 메모리 상에서 조작하기 위한 클래스
- 필요한 기능을 인터페이스에 정리하면서 시작
  - 회원 등록
  - 목록 조회
  - 회원 정보 수정
  - 회원 정보 삭제
  - exits() -> private 메서드로 수정
- List 대신 Iterator로 접근
- 중복 코드 추출 및 재활용