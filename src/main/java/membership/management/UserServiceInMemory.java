package membership.management;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 메모리상의 유저 정보를 관리하는 클래스
 */

public class UserServiceInMemory implements UserService {
    private List<User> users;
    
    public UserServiceInMemory() {
        this.users = new ArrayList<>();
    }
    
    public UserServiceInMemory(List<User> users) {
        this.users = users;
    }
    
    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public boolean updateUser(User user) {
        if(deleteUser(user.getEmail())) {
            users.add(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUser(String email) {
        boolean doesEmailExits = exists(email);
        if(doesEmailExits) {
            int index = findIndex(email);
            users.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // Users 의 정보를 그대로 return 할 것인가 복사한 후 return할 것인가?
    // 깊은 복사를 할 경우 외부에서 데이터를 조작할 수 있음 > List<User> 대신 읽기 전용인 Iterator<User>를 사용한다.
    @Override
    public Iterator<User> getUsers() {
        return users.iterator();
    }

    @Override
    public boolean exists(String email) {
        return findIndex(email) > -1;
    }
    
    private int findIndex(String email) {
        int findIndex = -1;
        for(int i = 0; i < users.size(); i ++) {
            if(users.get(i).getEmail().equals(email)) {
                findIndex = i;
                break;
            }
        }
        return findIndex;
    }
}
