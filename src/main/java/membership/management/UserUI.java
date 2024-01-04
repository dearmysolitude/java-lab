package membership.management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class UserUI {
    private BufferedReader br;
    public UserUI() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public int menu() {
        System.out.println("1. 회원 등록");
        System.out.println("2. 회원 목록 보기");
        System.out.println("3. 회원 수정하기");
        System.out.println("5. 종료");
        int menuId = -1;
        
        try {
            String line = br.readLine();
            menuId = Integer.parseInt(line);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return menuId;
    }
    
    public User regUser() {
        try {
            System.out.println("email을 입력하세요");
            String email = br.readLine();
            System.out.println("이름을 입력하세요.");
            String name = br.readLine();
            System.out.println("생년을 입력하세요.");
            String strBirthYear = br.readLine();
            int birthYear = Integer.parseInt(strBirthYear);

            return new User(email, name, birthYear);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void printUserList(List<User> users) {
        System.out.println("MemberID                   email                  이름                 생년");
        System.out.println("============================================================================");
        for(int i = 0; i < users.size(); i ++) {
            User user = users.get(i);
            System.out.println(i);
            System.out.print("           ");
            System.out.print(user.getEmail());
            System.out.print("           ");
            System.out.print(user.getName());
            System.out.print("           ");
            System.out.println(user.getBirthYear());
            System.out.println();
        }
    }
    
    public void editUser(User user) {
        
        try {
            System.out.println("email을 입력하세요");
            String email = br.readLine();
            System.out.println("이름을 입력하세요.");
            String name = br.readLine();
            System.out.println("생년을 입력하세요.");
            String strBirthYear = br.readLine();
            int birthYear = Integer.parseInt(strBirthYear);
            
            user.setEmail(email);
            user.setName(name);
            user.setBirthYear(birthYear);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
