package realtimeCMS; // Real-Time Courier Monitoring System
import java.lang.String;
import java.util.*;

public class Main {
    
    
    //LinkedList<Order> requestQue = new LinkedList<Order>();
    
    
    public static int probability() { 
        return (int)(Math.random()*100); // 0 ~ 99 중 하나의 값을 반환
    }

    public static void main(String [] args) {
        int time = 0; // 경과시간
        int orderRate = 55; // 분당 주문률[0 ~ 100 중 하나의 정수 : %], 나중에 사용자 입력이 가능할 수 있는 요소
        int ordercount = 1; // 주문번호에 해당
        StorageSpace stock = new StorageSpace(); // 창고공간 클래스 객체
        LinkedList<Order> orderQue = new LinkedList<Order>(); // 생성된 주문에 대한 대기열

        System.out.printf("경과시간 : %d\n\n", time); // 경과시간 : time
        System.out.println("<신규주문>\n주문번호 상품고유번호 상품명 수량 주소 적재부피 로켓배송 주문상태");
        for (int min = 0; min < 60; min++) {
            if ( orderRate > probability() ) { // 55% 확률로 주문생성
                Order order = new Order(probability() + 1, ordercount++, stock, Area.destination[(int)(((double)probability()) / 10)], (probability() % 2 == 0));
                orderQue.add(order);
            }
        }        
        System.out.println(Area.destination[0]);
        
    }    
}