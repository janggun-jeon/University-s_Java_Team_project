package realtimeCMS; // Real-Time Courier Monitoring System
import java.lang.String;
import java.util.*;


public class Main {
    public static int probability(int n) { // (n >= 2)인 정수
        return (int)(Math.random()*n); // 0 ~ n-1 중 하나의 값을 반환
    }

    public static void main(String [] args) {
        int time = 0; // 경과시간
        int orderRate = 25; // 분당 주문률[0 ~ 100 중 하나의 정수 : %], 나중에 사용자 입력이 가능할 수 있는 요소
        int ordercount = 1; // 주문번호에 해당
        StorageSpace stock = new StorageSpace(); // 창고공간 클래스 객체
        LinkedList<Order> orderQue = new LinkedList<Order>(); // 생성된 주문에 대한 대기열
        LinkedList<Order> requestQue = new LinkedList<Order>(); // orderQue에 order들을 다음 사이클때 출하하기 전에 현재 사이클에 저장하기 위한 대기열
        ReceivedSpace received = new ReceivedSpace(100, 10) ; // 납품공간 클래스 객체

    do {
        System.out.printf("\n경과시간 : %d\n\n", time); // 경과시간 : time
        
        System.out.println("<입고목록>\n상품명 | 발주량 | 파손량 | 입고량");
        {
            received.receive(stock.request()); 
        }

        System.out.println("\n<품절주문목록>\n주문번호 | 상품고유번호 | 상품명 | 수량 | 주소 | 적재부피 | 주문상태 | 로켓배송");
        { // 이전 타임의 주문된 order들을 먼저 현재 타임에 출하시도 후에 새 주문 접수
            Iterator <Order> it = requestQue.iterator(); // requestQue의 order들을 출하하기 위해 블록에서만 사용될 Iterator 참조를 선언해줌
            while(it.hasNext()) {
                Order order = it.next();
                stock.shipment(order) ;
                if (order.count <= 0) { // 품절된 경우
                    order.printState(); // 품절상태 출력
                }
            }
            requestQue.clear(); // 객체의 삭제가 아닌 arraylist와 내부객체들의 연결을 삭제
        }

        System.out.println("\n<신규주문>\n주문번호 | 상품고유번호 | 상품명 | 수량 | 주소 | 적재부피 | 주문상태 | 로켓배송");
        {
            for (int min = 0; min < 60; min++) {
                if ( orderRate > probability(100) ) { // 55% 확률로 주문생성 
                    Order order = new Order(ordercount++, probability(100) + 1, stock, Area.destination[probability(10)], (probability(100) % 2 == 0));
                    orderQue.add(order);
                }
            }
        }
        
        {      
            Iterator <Order> it = orderQue.iterator(); // orderQue의 요소를 requestQue로 옮기기 위해 블록에서만 사용될 Iterator 참조를 선언해줌
            while (it.hasNext()) {
                requestQue.add(it.next());
            }
            orderQue.clear(); // 객체의 삭제가 아닌 arraylist와 내부객체들의 연결을 삭제
        }
        System.out.println();
    } while (time++ < 16);
        
    }    
}