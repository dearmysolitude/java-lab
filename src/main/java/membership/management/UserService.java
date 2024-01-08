package membership.management;

import java.util.Iterator;

public interface UserService {
    
    //회원 등록
    public void addUser(User user);
    // 회원 목록 확인
    public boolean updateUser(User user); // 작업 성공 여부를 리턴
    // 회원 정보 수정
    public boolean deleteUser(String email); // 작업 성공 여부를 리턴
    // 모든 회원 정보를 반환한다.
    public Iterator<User> getUsers();
}
