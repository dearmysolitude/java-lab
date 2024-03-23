package basic_java;

import java.util.Scanner;

public class CalendarApp {
    public static void main(String[] args) {
        runCalendar();
    }

    private static void runCalendar() {
        Scanner sc = new Scanner(System.in);
        
        // 0. 입력 및 초기화: 년도와 달 입력 받기
        System.out.print("년도를 입력하세요: ");
        int year = sc.nextInt(); // 입력 예외 처리
        System.out.print("달을 입력하세요: ");
        int month = sc.nextInt(); // 입력 예외 처리
        int leapYearCount = 0; // 윤년의 수
        int dayCountFromJanFirst = 0; // 입력한 해 1월 1일부터 입력 달 이전까지의 날 수
        int dayNameOfFirst = 0; // 입력한 달의 첫째 날 요일

        // 1. 입력된 달의 1 일의 요일 구하기: 1년 1월 1일은 월요일이고, 해가 지날때마다 한 요일씩 밀린다. 윤년일 경우 한 요일 더 밀린다.
        // 윤년의 갯수: 4년에 한 번 윤년, 100 년에 한번(4의 배수지만) 평년, 400년에 한 번 100 년의 배수지만 윤년 
        leapYearCount = (year / 4) - (year / 100) + (year / 400);
        // 올해 윤년인 것은 아직 포함시키지 않는다: 윤달을 지나야 포함시킨다.
        if(leapYear(year)) {
            leapYearCount -= 1; 
        }
        
        // 2-1. 해당 월의 1 일이 무슨 요일인지 구하기: 전월까지의 모든 날을 더한다.
        dayCountFromJanFirst = getDayCountFromJanFirst(month);
        // 2-2. 윤년에는 2 월이 28 일까지가 아니라 29일까지 있다.
        if(leapMonthIncluded(year, month)) {
            dayCountFromJanFirst += 1;
        }
        
        // 3. 해가 갈 수록 요일이 1일 씩 밀리고, 윤년에는 2일 씩 밀리므로 Jan 1의 요일을 구할 수 있다.
        // 여기에 원하는 달 이전까지 날 수를 모두 더하여 7로 나누면 출력 하고자 하는 달의 1일의 요일을 구할 수 있다.
        dayNameOfFirst = (year + leapYearCount + dayCountFromJanFirst) % 7;

        // 4. 달력 출력
        printCalendar(year, month, dayNameOfFirst);
    }

    private static void printCalendar(int year, int month, int dayNameOfFirst) {
        int totalNumberOfDate = getDayCountFromJanFirst(month+1) - getDayCountFromJanFirst(month); //출력할 총 날짜
        int dateToPrint = 1; // 출력중인 날짜

        if(leapYear(year) && month == 2) { // 윤달일 경우 출력할 총 날짜 추가
            ++totalNumberOfDate;
        }
        
        System.out.print("Sun\tMon\tTue\tWed\tThu\tFri\tSat\n");
        dateToPrint = printFirstRow(dayNameOfFirst, dateToPrint); // 출력중인 날짜를 업데이트하고 첫 줄 출력
        printRestRow(totalNumberOfDate, dateToPrint); // 나머지 줄 출력
    }

    private static void printRestRow(int totalNumberOfDate, int dateToPrint) {
        while(dateToPrint < totalNumberOfDate) {
            for (int l = 0; l < 7; l++) { // 한 줄 출력
                if(dateToPrint == totalNumberOfDate) { // 출력을 끝냈으면 이 줄을 더이상 출력하지 않는다.
                    System.out.print(dateToPrint);
                    break;
                }
                System.out.print(dateToPrint+"\t");
                ++dateToPrint;
            }
            System.out.println();
        }
    }

    private static int printFirstRow(int dayNameOfFirst, int dateToPrint) {
        for(int j = 0; j < dayNameOfFirst; j++) { // 빈 칸 출력
            System.out.print("\t");
        }
        for(int k = 0; k < (7 - dayNameOfFirst); k++) { // 날짜 출력
            System.out.print(dateToPrint +"\t");
            ++dateToPrint;
        }
        System.out.println();
        return dateToPrint;
    }

    private static int getDayCountFromJanFirst(int month) {
        int dayCountFromJanFirst = 0;
        switch(month) { // 주의: 12월 첫날을 구하려면 1월 ~ 11월의 날짜를 더해야 한다
            case 12:
                dayCountFromJanFirst += 30;
            case 11:
                dayCountFromJanFirst += 31;
            case 10:
                dayCountFromJanFirst += 30;
            case 9:
                dayCountFromJanFirst += 31;
            case 8:
                dayCountFromJanFirst += 31;
            case 7:
                dayCountFromJanFirst += 30;
            case 6:
                dayCountFromJanFirst += 31;
            case 5:
                dayCountFromJanFirst += 30;
            case 4:
                dayCountFromJanFirst += 31;
            case 3:
                dayCountFromJanFirst += 28;
            case 2:
                dayCountFromJanFirst += 31;
            default:
        }
        return dayCountFromJanFirst;
    }
    
    // 윤년이고 2월(윤월) 이후라면 true 반환
    private static boolean leapMonthIncluded(int year, int month) {
        return leapYear(year) && month > 2;
    }
    
    // 윤년이라면 true 반환:  4로 나누어지고 100으로 안 나누어 떨어지면 윤년, 평년 중 400으로 나누어 떨어지면 윤년
    private static boolean leapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0)|| (year % 400 == 0);
    }
}
