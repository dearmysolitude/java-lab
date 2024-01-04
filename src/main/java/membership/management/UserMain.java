package membership.management;

import java.util.List;

public class UserMain {
    public static void main(String[] args) {
        UserUI userUI = new UserUI();
        UserDao userDao = new UserDao("./tmp/users.dat");
        List<User> users = userDao.getUsers();
        executeUI(userUI, userDao, users);
    }

    private static void executeUI(UserUI userUI, UserDao userDao, List<User> users) {
        while(true) {
            int menuId = userUI.menu(); 
            if(menuId == 5) {
                System.out.println("종료합니다.");
                userDao.saveUser(users);
                break;
            } else if(menuId == 1) {
                User user = userUI.regUser();
                users.add(user);
                System.out.println("등록되었습니다.");
            } else if(menuId == 2) {
                userUI.printUserList(users);
            } else if(menuId == 3) {
                System.out.println("수정할 유저의 일련 번호를 입력하세요.");
                userUI.printUserList(users);
                int memberId = -1;
                userUI.editUser(users.get(memberId));
            }
        }
    }
}
