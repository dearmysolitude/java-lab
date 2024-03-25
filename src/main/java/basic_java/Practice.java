package basic_java;

import java.util.Scanner;

public class Practice {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
    }
    public static void printBaseTenToBaseThree(int input) {
        if(input < 3) { // 입력이 3 보다 작을 경우 더이상 나누지 않고 그대로 input을 출력한다.
            System.out.print(input);
            return;
        }
        printBaseTenToBaseThree(input/3); // 재귀가 끝나면
        System.out.print(input%3); // 재귀를 탈출하는 순서대로 값을 순차적으로 출력(3으로 나눈 나머지)하도록 한다.
    }
}
