package realtimeCMS;
import java.lang.String;
import java.util.*;


class AreaOrders {//택배 발송 지역 클래스에 적재될 요소
    public int storage = 0;
    ArrayList<Order> orders = new ArrayList<Order>();
}


class Area{  //택배 발송 지역 지정 클래스.  main에서 한번만 생성할 예정.
    public static final int maxstorage = 25000;
    public static HashMap<String,AreaOrders > contents = new HashMap<String,AreaOrders>();
    public static String destination[] = {"강서","영등포", "송파", "성북", "용산", "강북", "서초", "강남", "종로", "마포"}; //지역 종류는 나중에 지정
    Area(){
        for (String s: destination) {
            contents.put(s,new AreaOrders());
        }  
    }
}

class WaitingOrder {//택배 용량이 부족하면 잠시 물류를 대기시키기 위해 만든 클래스. 원래라면 각 배송지별로 다르게 대기시켜야 하지만 일단은 하나의 스택에 적재 후 꺼낼 때가 되면 주문 내 배송지 정보를 통해 다시 찾아감
    public static Stack<Order> stack = new Stack<Order>();
}

//주문을 지역 택배에 적재. 만약에 현재 트럭의 용량에 주문의 무게를 더한 값이 임계값을 넘는다면 잠시 대기시키기위해 주문을 스택에 적재
class Pickup {
    Area area = new Area();
    //여기서 orders는 집화단계 클래스에서 처리가 끝나면 보낸 리스트를 받는 매개 객체입니다
    public void PickupOrder(ArrayList<Order> orders){// Area 클래스의 지역 택배 데이터를 불러온 뒤 무게 비교를 통해 들어온 주문들을 area클래스의 content에 저장할지, 대기 스택에 저장할지 판별
        int index=0;
        while(!orders.isEmpty()) {
            Order dispatchorder = orders.get(index);//인덱스를 올려가며 주문을 꺼낸다.
            AreaOrders AreaOrdersE=Area.contents.get(dispatchorder.address);//주문의 주소 속성을 이용해 해쉬맵에서 해당 주소를 찾은 뒤 값을 반환
            if (AreaOrdersE.storage+dispatchorder.totalvolume<=Area.maxstorage) {//크기 비교
                AreaOrdersE.storage+=dispatchorder.totalvolume;
                AreaOrdersE.orders.add(dispatchorder);
            }
            else {
                WaitingOrder.stack.add(dispatchorder);
            }
            orders.remove(index);//주문이 옮겨졌으므로 기존 클래스에 적재된 주문은 삭제
            index++;
        }
    }
    public void PickupStack() {//스택에 쌓인 주문들을 다시 꺼내어 pickuporder와 비슷한 방식으로 수행하는 함수. 만약에 여전히 트럭의 용량이 차있다면 해당 주문을 다시 스택의 최하단에 적재
        while (!WaitingOrder.stack.empty()) {
            Order stackOrder = WaitingOrder.stack.pop();
            AreaOrders AreaOrdersE=Area.contents.get(stackOrder.address);
            if (AreaOrdersE.storage+stackOrder.totalvolume<=Area.maxstorage) {
                AreaOrdersE.storage+=stackOrder.totalvolume;
                AreaOrdersE.orders.add(stackOrder);
            }
            else {
                WaitingOrder.stack.add(0, stackOrder);
            }
        }
    }
    public void SendOrder() {//택배의 용량의 70퍼센트가 채워졌고 주문의 개수가 10건이 넘는다면 상차. -> 무게를 부피로 바꾸었으므로 이 조건도 추후 바꿀 예정
        AreaOrders order;
        for (String s: Area.destination) {
            order=Area.contents.get(s);
            if (order.storage>=Area.maxstorage*0.7 && order.orders.size()>10) {
                order.storage=0;//저장공간을 0으로 하고 초기화
                order.orders.clear();
            }
        }  
    }
}

