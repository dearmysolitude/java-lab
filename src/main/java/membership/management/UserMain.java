package membership.management;

import java.util.Iterator;
import java.util.List;

public class UserMain {
    public static void main(String[] args) {
        UserUI userUI = new UserUI();
        UserDao userDao = new UserDao("./tmp/users.dat");
        UserService userService = new UserServiceInMemory(userDao.getUsers());
        executeUI(userUI, userDao, userService);
    }

    private static void executeUI(UserUI userUI, UserDao userDao, UserService userService) {
        while(true) {
            int menuId = userUI.menu(); 
            if(menuId == 5) {
                System.out.println("종료합니다.");
                userDao.saveUser(userService.getUsers());
                break;
            } else if(menuId == 1) {
                User user = userUI.regUser();
                userService.addUser(user);
                System.out.println("등록되었습니다.");
            } else if(menuId == 2) {
                userUI.printUserList(userService.getUsers());
            } else if(menuId == 3) {
                userUI.printUserList(userService.getUsers());
                String email = userUI.inputEmail();
                User updateUser = userUI.inputUser(email);

                if(userService.updateUser(updateUser)) {
                    System.out.println("회원 정보 수정");
                } else {
                    System.out.println("수정할 회원 정보가 없습니다.");
                }
            } else  if (menuId == 4) {
                userUI.printUserList(userService.getUsers());
                String email = userUI.inputEmail();
                
                if(userService.deleteUser(email)) {
                    System.out.println("회원 정보 삭제");
                } else {
                    System.out.println("삭제할 회원 정보가 없습니다.");
                }
            }
        }
    }
}
