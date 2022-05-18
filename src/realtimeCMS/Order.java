package realtimeCMS; // Real-Time Courier Monitoring System
import java.lang.String;
import java.util.*;

class State {
    static String process[] = {"품절됨", "주문됨", "출하됨", "작업중", "적재됨", "배송됨"};
}

class Order extends State {
    int orderNumber; // 주문번호
    int key; // 상품식별번호
    String productName; // 상품명
    int quantity; // 주문수량
    String address; // 배송주소
    double totalWeight; // 총 무게[kg] = 상품무게 X 주문수량 == 적재량
    boolean rocketDelivery; // 로켓배송여부
    String orderState; // 주문상태
    int count; // process의 인덱스를 변경시키는 변수
    public Order(int orderNumber, int key, StorageSpace store, String address, boolean rocketDelivery) {
        this.orderNumber = orderNumber;
        this.key = key;    
        this.productName = store.inventory.get(key).productName;
        this.quantity = store.inventory.get(key).quantity;
        this.address = address;
        this.totalWeight = ( store.inventory.get(key).weight )*(this.quantity);
        this.rocketDelivery = rocketDelivery;
        this.count = 1;
        this.orderState = State.process[count];
    }
    public String nextState() { // 다음 주문상태로 전환
        this.orderState = State.process[++count];
        return this.orderState;
    }
    public String sold_out() { // 품절
        this.orderState = State.process[--count];
        return this.orderState;

    }
}